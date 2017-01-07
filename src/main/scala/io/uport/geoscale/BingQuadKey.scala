/*
 * Copyright 2016 David Schmitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.uport.geoscale

import Spheroid._
import Direction._

/**
  * Created by schmitda on 29.11.16.
  */
case class BingQuadKey(key: String) {
  require(key.size >= 1, "Invalid key minimum level is 1")
  require(key.size <= 23, "Invalid key maximum level is 23")

  def level(): Int   = key.size
  def toTile(): Tile = BingQuadKey.toTile(key)

}

object BingQuadKey {

  def apply(coordinate: Coordinate, level: Int): BingQuadKey = {
    BingQuadKey(Tile(coordinate, level))
  }

  def apply(coordinate: Coordinate, levels: List[Int]): List[BingQuadKey] = {
    levels.map { level =>
      BingQuadKey(coordinate, level)
    }
  }

  def apply(tile: Tile): BingQuadKey = {
    val quadKey = new StringBuilder()

    for (i <- tile.level to 1 by -1) {
      var digit = 0
      val mask  = 1 << (i - 1)
      if ((tile.tileX & mask) != 0) {
        digit = digit + 1
      }
      if ((tile.tileY & mask) != 0) {
        digit = digit + 2
      }
      quadKey.append(digit)
    }
    BingQuadKey(quadKey.toString)
  }

  def toTile(quadKey: String): Tile = {
    var tileX = 0
    var tileY = 0
    val level = BingQuadKey(quadKey).level

    for (i <- level to 1 by -1) {
      val mask: Int = 1 << (i - 1)

      quadKey(level - i) match {
        case '0' =>
        case '1' => tileX |= mask
        case '2' => tileY |= mask
        case '3' =>
          tileX |= mask
          tileY |= mask
        case _ => None
      }
    }
    Tile(tileX, tileY, level)
  }

  private def keyCharTranslate(keyChar: Char, direction: Direction): Char = {
    keyChar match {
      case '0' => if (Direction.isHorizontal(direction)) '1' else '2'
      case '1' => if (Direction.isHorizontal(direction)) '0' else '3'
      case '2' => if (Direction.isHorizontal(direction)) '3' else '0'
      case '3' => if (Direction.isHorizontal(direction)) '2' else '1'
      case _ =>
        throw new IllegalArgumentException(s"KeyChar must be within [0-3], but was ${keyChar}")
    }
  }

  def keyTranslate(quadKey: String, index: Int, direction: Direction): String = {

    val savedChar = quadKey.charAt(index)
    val prefix    = quadKey.substring(0, index)
    val postfix   = if (index < quadKey.length - 1) quadKey.substring(index + 1) else ""

    var key = prefix + keyCharTranslate(quadKey.charAt(index), direction) + postfix

    if (index > 0) {
      if (((savedChar == '0') && (direction == Left || direction == Up)) ||
          ((savedChar == '1') && (direction == Right || direction == Up)) ||
          ((savedChar == '2') && (direction == Left || direction == Down)) ||
          ((savedChar == '3') && (direction == Right || direction == Down))) {
        key = keyTranslate(key, index - 1, direction)
      }
    }
    key
  }

}
