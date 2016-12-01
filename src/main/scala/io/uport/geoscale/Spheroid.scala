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

object Spheroid {

  final val EarthRadius: Double  = 6378137.0
  final val MinLatitude: Double  = -85.05112878
  final val MaxLatitude: Double  = 85.05112878
  final val MinLongitude: Double = -180.0
  final val MaxLongitude: Double = 180.0

  private def clip(n: Double, minValue: Double, maxValue: Double): Double = {
    Math.min(Math.max(n, minValue), maxValue)
  }

  def clipLatitude(latitude: Double): Double = clip(latitude, MinLatitude, MaxLatitude)

  def clipLongitude(longitude: Double): Double = clip(longitude, MinLongitude, MaxLongitude)

  case class Coordinate(latitude: Double, longitude: Double) {
    require(latitude >= MinLatitude && latitude <= MaxLatitude,
            s"Latitude out of range [${MinLatitude}, ${MaxLatitude}]")
    require(longitude >= MinLongitude && longitude <= MaxLongitude,
            s"Longitude out of range [${MinLongitude}, ${MaxLongitude}]")
  }

}
