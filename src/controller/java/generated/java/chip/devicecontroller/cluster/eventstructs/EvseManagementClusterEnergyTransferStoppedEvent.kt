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

class EvseManagementClusterEnergyTransferStoppedEvent (
    val evseSessionId: Long,
    val evseState: Int,
    val reason: Int,
    val energyTransferred: Long) {
  override fun toString(): String  = buildString {
    append("EvseManagementClusterEnergyTransferStoppedEvent {\n")
    append("\tevseSessionId : $evseSessionId\n")
    append("\tevseState : $evseState\n")
    append("\treason : $reason\n")
    append("\tenergyTransferred : $energyTransferred\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_EVSE_SESSION_ID), evseSessionId)
      put(ContextSpecificTag(TAG_EVSE_STATE), evseState)
      put(ContextSpecificTag(TAG_REASON), reason)
      put(ContextSpecificTag(TAG_ENERGY_TRANSFERRED), energyTransferred)
      endStructure()
    }
  }

  companion object {
    private const val TAG_EVSE_SESSION_ID = 0
    private const val TAG_EVSE_STATE = 1
    private const val TAG_REASON = 2
    private const val TAG_ENERGY_TRANSFERRED = 4

    fun fromTlv(tag: Tag, tlvReader: TlvReader) : EvseManagementClusterEnergyTransferStoppedEvent {
      tlvReader.enterStructure(tag)
      val evseSessionId = tlvReader.getLong(ContextSpecificTag(TAG_EVSE_SESSION_ID))
      val evseState = tlvReader.getInt(ContextSpecificTag(TAG_EVSE_STATE))
      val reason = tlvReader.getInt(ContextSpecificTag(TAG_REASON))
      val energyTransferred = tlvReader.getLong(ContextSpecificTag(TAG_ENERGY_TRANSFERRED))
      
      tlvReader.exitContainer()

      return EvseManagementClusterEnergyTransferStoppedEvent(evseSessionId, evseState, reason, energyTransferred)
    }
  }
}
