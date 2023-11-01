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
package chip.devicecontroller.cluster.structs

import chip.devicecontroller.cluster.*
import chip.tlv.AnonymousTag
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvReader
import chip.tlv.TlvWriter
import java.util.Optional

class DeviceEnergyManagementClusterPowerForecastStruct(
  val forecastId: Int,
  val activeSlotNumber: Int?,
  val startTime: Long,
  val endTime: Optional<Long>,
  val earliestStartTime: Optional<Long>?,
  val latestEndTime: Optional<Long>,
  val isPauseable: Boolean,
  val slots: List<DeviceEnergyManagementClusterSlotStruct>
) {
  override fun toString(): String = buildString {
    append("DeviceEnergyManagementClusterPowerForecastStruct {\n")
    append("\tforecastId : $forecastId\n")
    append("\tactiveSlotNumber : $activeSlotNumber\n")
    append("\tstartTime : $startTime\n")
    append("\tendTime : $endTime\n")
    append("\tearliestStartTime : $earliestStartTime\n")
    append("\tlatestEndTime : $latestEndTime\n")
    append("\tisPauseable : $isPauseable\n")
    append("\tslots : $slots\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_FORECAST_ID), forecastId)
      if (activeSlotNumber != null) {
        put(ContextSpecificTag(TAG_ACTIVE_SLOT_NUMBER), activeSlotNumber)
      } else {
        putNull(ContextSpecificTag(TAG_ACTIVE_SLOT_NUMBER))
      }
      put(ContextSpecificTag(TAG_START_TIME), startTime)
      if (endTime.isPresent) {
        val optendTime = endTime.get()
        put(ContextSpecificTag(TAG_END_TIME), optendTime)
      }
      if (earliestStartTime != null) {
        if (earliestStartTime.isPresent) {
          val optearliestStartTime = earliestStartTime.get()
          put(ContextSpecificTag(TAG_EARLIEST_START_TIME), optearliestStartTime)
        }
      } else {
        putNull(ContextSpecificTag(TAG_EARLIEST_START_TIME))
      }
      if (latestEndTime.isPresent) {
        val optlatestEndTime = latestEndTime.get()
        put(ContextSpecificTag(TAG_LATEST_END_TIME), optlatestEndTime)
      }
      put(ContextSpecificTag(TAG_IS_PAUSEABLE), isPauseable)
      startList(ContextSpecificTag(TAG_SLOTS))
      for (item in slots.iterator()) {
        item.toTlv(AnonymousTag, this)
      }
      endList()
      endStructure()
    }
  }

  companion object {
    private const val TAG_FORECAST_ID = 0
    private const val TAG_ACTIVE_SLOT_NUMBER = 1
    private const val TAG_START_TIME = 2
    private const val TAG_END_TIME = 3
    private const val TAG_EARLIEST_START_TIME = 4
    private const val TAG_LATEST_END_TIME = 5
    private const val TAG_IS_PAUSEABLE = 6
    private const val TAG_SLOTS = 7

    fun fromTlv(tag: Tag, tlvReader: TlvReader): DeviceEnergyManagementClusterPowerForecastStruct {
      tlvReader.enterStructure(tag)
      val forecastId = tlvReader.getInt(ContextSpecificTag(TAG_FORECAST_ID))
      val activeSlotNumber =
        if (!tlvReader.isNull()) {
          tlvReader.getInt(ContextSpecificTag(TAG_ACTIVE_SLOT_NUMBER))
        } else {
          tlvReader.getNull(ContextSpecificTag(TAG_ACTIVE_SLOT_NUMBER))
          null
        }
      val startTime = tlvReader.getLong(ContextSpecificTag(TAG_START_TIME))
      val endTime =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_END_TIME))) {
          Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_END_TIME)))
        } else {
          Optional.empty()
        }
      val earliestStartTime =
        if (!tlvReader.isNull()) {
          if (tlvReader.isNextTag(ContextSpecificTag(TAG_EARLIEST_START_TIME))) {
            Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_EARLIEST_START_TIME)))
          } else {
            Optional.empty()
          }
        } else {
          tlvReader.getNull(ContextSpecificTag(TAG_EARLIEST_START_TIME))
          null
        }
      val latestEndTime =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_LATEST_END_TIME))) {
          Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_LATEST_END_TIME)))
        } else {
          Optional.empty()
        }
      val isPauseable = tlvReader.getBoolean(ContextSpecificTag(TAG_IS_PAUSEABLE))
      val slots =
        buildList<DeviceEnergyManagementClusterSlotStruct> {
          tlvReader.enterList(ContextSpecificTag(TAG_SLOTS))
          while (!tlvReader.isEndOfContainer()) {
            add(DeviceEnergyManagementClusterSlotStruct.fromTlv(AnonymousTag, tlvReader))
          }
          tlvReader.exitContainer()
        }

      tlvReader.exitContainer()

      return DeviceEnergyManagementClusterPowerForecastStruct(
        forecastId,
        activeSlotNumber,
        startTime,
        endTime,
        earliestStartTime,
        latestEndTime,
        isPauseable,
        slots
      )
    }
  }
}
