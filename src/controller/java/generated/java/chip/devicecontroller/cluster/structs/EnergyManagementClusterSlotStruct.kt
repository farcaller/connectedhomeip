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

class EnergyManagementClusterSlotStruct(
  val minDuration: Long,
  val maxDuration: Long,
  val defaultDuration: Long,
  val elapsedSlotTime: Long,
  val remainingSlotTime: Long,
  val slotIsPauseable: Boolean,
  val nominalPower: Int,
  val minPower: Int,
  val maxPower: Int,
  val nominalEnergy: Long,
  val costs: Optional<List<EnergyManagementClusterCostStruct>>,
  val minPowerAdjustment: Int,
  val maxPowerAdjustment: Int,
  val minimumDurationAdjustment: Long,
  val maximumDurationAdjustment: Long
) {
  override fun toString(): String = buildString {
    append("EnergyManagementClusterSlotStruct {\n")
    append("\tminDuration : $minDuration\n")
    append("\tmaxDuration : $maxDuration\n")
    append("\tdefaultDuration : $defaultDuration\n")
    append("\telapsedSlotTime : $elapsedSlotTime\n")
    append("\tremainingSlotTime : $remainingSlotTime\n")
    append("\tslotIsPauseable : $slotIsPauseable\n")
    append("\tnominalPower : $nominalPower\n")
    append("\tminPower : $minPower\n")
    append("\tmaxPower : $maxPower\n")
    append("\tnominalEnergy : $nominalEnergy\n")
    append("\tcosts : $costs\n")
    append("\tminPowerAdjustment : $minPowerAdjustment\n")
    append("\tmaxPowerAdjustment : $maxPowerAdjustment\n")
    append("\tminimumDurationAdjustment : $minimumDurationAdjustment\n")
    append("\tmaximumDurationAdjustment : $maximumDurationAdjustment\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_MIN_DURATION), minDuration)
      put(ContextSpecificTag(TAG_MAX_DURATION), maxDuration)
      put(ContextSpecificTag(TAG_DEFAULT_DURATION), defaultDuration)
      put(ContextSpecificTag(TAG_ELAPSED_SLOT_TIME), elapsedSlotTime)
      put(ContextSpecificTag(TAG_REMAINING_SLOT_TIME), remainingSlotTime)
      put(ContextSpecificTag(TAG_SLOT_IS_PAUSEABLE), slotIsPauseable)
      put(ContextSpecificTag(TAG_NOMINAL_POWER), nominalPower)
      put(ContextSpecificTag(TAG_MIN_POWER), minPower)
      put(ContextSpecificTag(TAG_MAX_POWER), maxPower)
      put(ContextSpecificTag(TAG_NOMINAL_ENERGY), nominalEnergy)
      if (costs.isPresent) {
        val optcosts = costs.get()
        startList(ContextSpecificTag(TAG_COSTS))
        for (item in optcosts.iterator()) {
          item.toTlv(AnonymousTag, this)
        }
        endList()
      }
      put(ContextSpecificTag(TAG_MIN_POWER_ADJUSTMENT), minPowerAdjustment)
      put(ContextSpecificTag(TAG_MAX_POWER_ADJUSTMENT), maxPowerAdjustment)
      put(ContextSpecificTag(TAG_MINIMUM_DURATION_ADJUSTMENT), minimumDurationAdjustment)
      put(ContextSpecificTag(TAG_MAXIMUM_DURATION_ADJUSTMENT), maximumDurationAdjustment)
      endStructure()
    }
  }

  companion object {
    private const val TAG_MIN_DURATION = 0
    private const val TAG_MAX_DURATION = 1
    private const val TAG_DEFAULT_DURATION = 2
    private const val TAG_ELAPSED_SLOT_TIME = 3
    private const val TAG_REMAINING_SLOT_TIME = 4
    private const val TAG_SLOT_IS_PAUSEABLE = 5
    private const val TAG_NOMINAL_POWER = 6
    private const val TAG_MIN_POWER = 7
    private const val TAG_MAX_POWER = 8
    private const val TAG_NOMINAL_ENERGY = 9
    private const val TAG_COSTS = 10
    private const val TAG_MIN_POWER_ADJUSTMENT = 11
    private const val TAG_MAX_POWER_ADJUSTMENT = 12
    private const val TAG_MINIMUM_DURATION_ADJUSTMENT = 13
    private const val TAG_MAXIMUM_DURATION_ADJUSTMENT = 14

    fun fromTlv(tag: Tag, tlvReader: TlvReader): EnergyManagementClusterSlotStruct {
      tlvReader.enterStructure(tag)
      val minDuration = tlvReader.getLong(ContextSpecificTag(TAG_MIN_DURATION))
      val maxDuration = tlvReader.getLong(ContextSpecificTag(TAG_MAX_DURATION))
      val defaultDuration = tlvReader.getLong(ContextSpecificTag(TAG_DEFAULT_DURATION))
      val elapsedSlotTime = tlvReader.getLong(ContextSpecificTag(TAG_ELAPSED_SLOT_TIME))
      val remainingSlotTime = tlvReader.getLong(ContextSpecificTag(TAG_REMAINING_SLOT_TIME))
      val slotIsPauseable = tlvReader.getBoolean(ContextSpecificTag(TAG_SLOT_IS_PAUSEABLE))
      val nominalPower = tlvReader.getInt(ContextSpecificTag(TAG_NOMINAL_POWER))
      val minPower = tlvReader.getInt(ContextSpecificTag(TAG_MIN_POWER))
      val maxPower = tlvReader.getInt(ContextSpecificTag(TAG_MAX_POWER))
      val nominalEnergy = tlvReader.getLong(ContextSpecificTag(TAG_NOMINAL_ENERGY))
      val costs =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_COSTS))) {
          Optional.of(
            buildList<EnergyManagementClusterCostStruct> {
              tlvReader.enterList(ContextSpecificTag(TAG_COSTS))
              while (!tlvReader.isEndOfContainer()) {
                add(EnergyManagementClusterCostStruct.fromTlv(AnonymousTag, tlvReader))
              }
              tlvReader.exitContainer()
            }
          )
        } else {
          Optional.empty()
        }
      val minPowerAdjustment = tlvReader.getInt(ContextSpecificTag(TAG_MIN_POWER_ADJUSTMENT))
      val maxPowerAdjustment = tlvReader.getInt(ContextSpecificTag(TAG_MAX_POWER_ADJUSTMENT))
      val minimumDurationAdjustment =
        tlvReader.getLong(ContextSpecificTag(TAG_MINIMUM_DURATION_ADJUSTMENT))
      val maximumDurationAdjustment =
        tlvReader.getLong(ContextSpecificTag(TAG_MAXIMUM_DURATION_ADJUSTMENT))

      tlvReader.exitContainer()

      return EnergyManagementClusterSlotStruct(
        minDuration,
        maxDuration,
        defaultDuration,
        elapsedSlotTime,
        remainingSlotTime,
        slotIsPauseable,
        nominalPower,
        minPower,
        maxPower,
        nominalEnergy,
        costs,
        minPowerAdjustment,
        maxPowerAdjustment,
        minimumDurationAdjustment,
        maximumDurationAdjustment
      )
    }
  }
}
