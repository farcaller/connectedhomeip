/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *    All rights reserved.
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

#pragma once

#include <stdbool.h>
#include <stdint.h>

#include <functional>

#include <lib/core/CHIPError.h>

bool emberAfEvseManagementClusterDisableEvseChargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::DisableEvseCharging::DecodableType & commandData);

bool emberAfEvseManagementClusterEnsableEvseChargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::EnsableEvseCharging::DecodableType & commandData);

bool emberAfEvseManagementClusterEnableEvseDischargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::EnableEvseDischarging::DecodableType & commandData);

bool emberAfEvseManagementClusterStartDiagnosticsCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::StartDiagnostics::DecodableType & commandData);


class EvseManagementManager
{
public:
    static void Shutdown();
    CHIP_ERROR Init();

private:
    friend EvseManagementManager & EvseManagementMgr(void);

    static EvseManagementManager sEvseManagement;
};

inline EvseManagementManager & EvseManagementMgr(void)
{
    return EvseManagementManager::sEvseManagement;
}
