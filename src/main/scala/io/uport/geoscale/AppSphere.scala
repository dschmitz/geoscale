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

/**
  * Created by schmitda on 28.11.16.
  */
object AppSphere extends App {

  val tile    = Tile(1, 4, 10)
  val quadKey = BingQuadKey(tile)
  println(tile)
  val revTile: Tile = quadKey.toTile()
  println(quadKey)

  println(revTile)
}
