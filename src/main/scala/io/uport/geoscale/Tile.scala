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

/**
  * Created by schmitda on 29.11.16.
  */
case class Tile(tileX: Int, tileY: Int, level: Int)

object Tile {

  def apply(coordinate: Coordinate, level: Int): Tile = {
    val clippedLatitude  = clipLatitude(coordinate.latitude)
    val clippedLongitude = clipLongitude(coordinate.longitude)

    val latRad = clippedLatitude * Math.PI / 180
    val n      = Math.pow(2, level)
    val xTile  = n * ((clippedLongitude + 180) / 360)
    val yTile  = n * (1 - (Math.log(Math.tan(latRad) + 1 / Math.cos(latRad)) / Math.PI)) / 2

    Tile(xTile.asInstanceOf[Int], yTile.asInstanceOf[Int], level)
  }

}
