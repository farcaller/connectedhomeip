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
import chip.tlv.AnonymousTag
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvParsingException
import chip.tlv.TlvReader
import chip.tlv.TlvWriter

import java.util.Optional

class EnergyManagementClusterPowerAdjustEndEvent (
    val cause: Optional<Int>,
    val duration: Optional<Long>,
    val energyUse: Optional<Long>) {
  override fun toString(): String  = buildString {
    append("EnergyManagementClusterPowerAdjustEndEvent {\n")
    append("\tcause : $cause\n")
    append("\tduration : $duration\n")
    append("\tenergyUse : $energyUse\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      if (cause.isPresent) {
      val optcause = cause.get()
      put(ContextSpecificTag(TAG_CAUSE), optcause)
    }
      if (duration.isPresent) {
      val optduration = duration.get()
      put(ContextSpecificTag(TAG_DURATION), optduration)
    }
      if (energyUse.isPresent) {
      val optenergyUse = energyUse.get()
      put(ContextSpecificTag(TAG_ENERGY_USE), optenergyUse)
    }
      endStructure()
    }
  }

  companion object {
    private const val TAG_CAUSE = 0
    private const val TAG_DURATION = 1
    private const val TAG_ENERGY_USE = 2

    fun fromTlv(tag: Tag, tlvReader: TlvReader) : EnergyManagementClusterPowerAdjustEndEvent {
      tlvReader.enterStructure(tag)
      val cause = if (tlvReader.isNextTag(ContextSpecificTag(TAG_CAUSE))) {
      Optional.of(tlvReader.getInt(ContextSpecificTag(TAG_CAUSE)))
    } else {
      Optional.empty()
    }
      val duration = if (tlvReader.isNextTag(ContextSpecificTag(TAG_DURATION))) {
      Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_DURATION)))
    } else {
      Optional.empty()
    }
      val energyUse = if (tlvReader.isNextTag(ContextSpecificTag(TAG_ENERGY_USE))) {
      Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_ENERGY_USE)))
    } else {
      Optional.empty()
    }
      
      tlvReader.exitContainer()

      return EnergyManagementClusterPowerAdjustEndEvent(cause, duration, energyUse)
    }
  }
}
