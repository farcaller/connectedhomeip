/*
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

#include "DefaultEvseManagementDelegate.h"
#include "evse-management-delegate.h"

#include <app-common/zap-generated/attributes/Accessors.h>
#include <app-common/zap-generated/cluster-objects.h>
#include <app-common/zap-generated/ids/Attributes.h>
#include <app-common/zap-generated/ids/Clusters.h>
#include <app/AttributeAccessInterface.h>
#include <app/CommandHandler.h>
#include <app/ConcreteCommandPath.h>
#include <app/util/af.h>
#include <app/util/attribute-storage.h>
#include <lib/core/CHIPEncoding.h>
#include <lib/core/Optional.h>
#include <lib/support/CHIPPlatformMemory.h>
#include <platform/CHIPDeviceLayer.h>
#include <platform/ConnectivityManager.h>

using namespace chip;
using namespace chip::app;
using namespace chip::app::Clusters;
using namespace chip::app::Clusters::EvseManagement;
using namespace chip::app::Clusters::EvseManagement::Attributes;
using chip::Protocols::InteractionModel::Status;

// Delegate
// Implementation
namespace {

Delegate * gDelegate = nullptr;

Delegate * GetDelegate()
{
    if (gDelegate == nullptr)
    {
        static DefaultEvseManagementDelegate dg;
        gDelegate = &dg;
    }
    return gDelegate;
}

} // namespace

namespace chip {
namespace app {
namespace Clusters {
namespace EvseManagement {

void SetDefaultDelegate(EndpointId endpoint, Delegate * delegate)
{
    gDelegate = delegate;
}

Delegate * GetDefaultDelegate()
{
    return GetDelegate();
}

} // namespace EvseManagement
} // namespace Clusters
} // namespace app
} // namespace chip

void MatterEvseManagementPluginServerInitCallback() {}

void emberAfEvseManagementClusterServerInitCallback(chip::EndpointId endpoint) {}

bool emberAfEvseManagementClusterDisableEvseCallback(app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
                                                     const Commands::DisableEvse::DecodableType & commandData)
{
    Status status = Status::Success;

    status = (CHIP_NO_ERROR == GetDelegate()->DisableEvseCharging()) ? Status::Success : Status::Failure;
    commandObj->AddStatus(commandPath, status);

    return true;
}

bool emberAfEvseManagementClusterEnableEvseChargingCallback(app::CommandHandler * commandObj,
                                                            const app::ConcreteCommandPath & commandPath,
                                                            const Commands::EnableEvseCharging::DecodableType & commandData)
{
    Status status = Status::Success;

    const auto & evseEnableTime       = commandData.evseEnableTime;
    const auto & minimumChargeCurrent = commandData.minimumChargeCurrent;
    const auto & maximumChargeCurrent = commandData.maximumChargeCurrent;

    status = (CHIP_NO_ERROR == GetDelegate()->EnableEvseCharging(evseEnableTime, minimumChargeCurrent, maximumChargeCurrent))
        ? Status::Success
        : Status::Failure;

    commandObj->AddStatus(commandPath, status);

    return true;
}

bool emberAfEvseManagementClusterEnableEvseDischargingCallback(app::CommandHandler * commandObj,
                                                               const app::ConcreteCommandPath & commandPath,
                                                               const Commands::EnableEvseDischarging::DecodableType & commandData)
{
    Status status = Status::Success;

    const auto & evseEnableTime          = commandData.evseEnableTime;
    const auto & maximumDischargeCurrent = commandData.maximumDischargeCurrent;

    status = (CHIP_NO_ERROR == GetDelegate()->EnableEvseDischarging(evseEnableTime, maximumDischargeCurrent)) ? Status::Success
                                                                                                              : Status::Failure;

    commandObj->AddStatus(commandPath, status);

    return true;
}

bool emberAfEvseManagementClusterStartDiagnosticsCallback(app::CommandHandler * commandObj,
                                                          const app::ConcreteCommandPath & commandPath,
                                                          const Commands::StartDiagnostics::DecodableType & commandData)
{
    Status status = Status::Success;

    status = (CHIP_NO_ERROR == GetDelegate()->StartDiagnostics()) ? Status::Success : Status::Failure;
    commandObj->AddStatus(commandPath, status);

    return true;
}
bool emberAfEvseManagementClusterSetTargetsCallback(app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
                                                    const Commands::SetTargets::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEvseManagementClusterGetTargetsCallback(app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
                                                    const Commands::GetTargets::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEvseManagementClusterClearTargetsCallback(app::CommandHandler * commandObj,
                                                      const app::ConcreteCommandPath & commandPath,
                                                      const Commands::ClearTargets::DecodableType & commandData)
{
    // TODO
    return false;
}
