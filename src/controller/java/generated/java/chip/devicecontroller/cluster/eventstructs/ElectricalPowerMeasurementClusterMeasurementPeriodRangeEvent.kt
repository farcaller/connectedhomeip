/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package chip.devicecontroller.cluster.eventstructs

import chip.devicecontroller.cluster.*
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvReader
import chip.tlv.TlvWriter

class ElectricalPowerMeasurementClusterMeasurementPeriodRangeEvent(
  val measurementType: Int,
  val periodStart: Long,
  val periodEnd: Long,
  val min: Long,
  val minTimestamp: Long,
  val max: Long,
  val maxTimestamp: Long
) {
  override fun toString(): String = buildString {
    append("ElectricalPowerMeasurementClusterMeasurementPeriodRangeEvent {\n")
    append("\tmeasurementType : $measurementType\n")
    append("\tperiodStart : $periodStart\n")
    append("\tperiodEnd : $periodEnd\n")
    append("\tmin : $min\n")
    append("\tminTimestamp : $minTimestamp\n")
    append("\tmax : $max\n")
    append("\tmaxTimestamp : $maxTimestamp\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_MEASUREMENT_TYPE), measurementType)
      put(ContextSpecificTag(TAG_PERIOD_START), periodStart)
      put(ContextSpecificTag(TAG_PERIOD_END), periodEnd)
      put(ContextSpecificTag(TAG_MIN), min)
      put(ContextSpecificTag(TAG_MIN_TIMESTAMP), minTimestamp)
      put(ContextSpecificTag(TAG_MAX), max)
      put(ContextSpecificTag(TAG_MAX_TIMESTAMP), maxTimestamp)
      endStructure()
    }
  }

  companion object {
    private const val TAG_MEASUREMENT_TYPE = 0
    private const val TAG_PERIOD_START = 1
    private const val TAG_PERIOD_END = 2
    private const val TAG_MIN = 3
    private const val TAG_MIN_TIMESTAMP = 4
    private const val TAG_MAX = 5
    private const val TAG_MAX_TIMESTAMP = 6

    fun fromTlv(
      tag: Tag,
      tlvReader: TlvReader
    ): ElectricalPowerMeasurementClusterMeasurementPeriodRangeEvent {
      tlvReader.enterStructure(tag)
      val measurementType = tlvReader.getInt(ContextSpecificTag(TAG_MEASUREMENT_TYPE))
      val periodStart = tlvReader.getLong(ContextSpecificTag(TAG_PERIOD_START))
      val periodEnd = tlvReader.getLong(ContextSpecificTag(TAG_PERIOD_END))
      val min = tlvReader.getLong(ContextSpecificTag(TAG_MIN))
      val minTimestamp = tlvReader.getLong(ContextSpecificTag(TAG_MIN_TIMESTAMP))
      val max = tlvReader.getLong(ContextSpecificTag(TAG_MAX))
      val maxTimestamp = tlvReader.getLong(ContextSpecificTag(TAG_MAX_TIMESTAMP))

      tlvReader.exitContainer()

      return ElectricalPowerMeasurementClusterMeasurementPeriodRangeEvent(
        measurementType,
        periodStart,
        periodEnd,
        min,
        minTimestamp,
        max,
        maxTimestamp
      )
    }
  }
}
