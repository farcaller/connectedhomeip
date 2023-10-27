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

class EnergyEvseClusterEnergyTransferStartedEvent(
  val sessionId: Long,
  val state: Int,
  val maximumCurrent: Long
) {
  override fun toString(): String = buildString {
    append("EnergyEvseClusterEnergyTransferStartedEvent {\n")
    append("\tsessionId : $sessionId\n")
    append("\tstate : $state\n")
    append("\tmaximumCurrent : $maximumCurrent\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_SESSION_ID), sessionId)
      put(ContextSpecificTag(TAG_STATE), state)
      put(ContextSpecificTag(TAG_MAXIMUM_CURRENT), maximumCurrent)
      endStructure()
    }
  }

  companion object {
    private const val TAG_SESSION_ID = 0
    private const val TAG_STATE = 1
    private const val TAG_MAXIMUM_CURRENT = 2

    fun fromTlv(tag: Tag, tlvReader: TlvReader): EnergyEvseClusterEnergyTransferStartedEvent {
      tlvReader.enterStructure(tag)
      val sessionId = tlvReader.getLong(ContextSpecificTag(TAG_SESSION_ID))
      val state = tlvReader.getInt(ContextSpecificTag(TAG_STATE))
      val maximumCurrent = tlvReader.getLong(ContextSpecificTag(TAG_MAXIMUM_CURRENT))

      tlvReader.exitContainer()

      return EnergyEvseClusterEnergyTransferStartedEvent(sessionId, state, maximumCurrent)
    }
  }
}
