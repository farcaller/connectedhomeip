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
using namespace chip::app::Clusters::EnergyManagement;
using namespace chip::app::Clusters::EnergyManagement::Attributes;

void MatterEnergyManagementPluginServerInitCallback() {}

void emberAfEnergyManagementClusterServerInitCallback(chip::EndpointId endpoint) {}

bool emberAfEnergyManagementClusterPowerAdjustRequestCallback(app::CommandHandler * commandObj,
                                                              const app::ConcreteCommandPath & commandPath,
                                                              const Commands::PowerAdjustRequest::DecodableType & commandData)
{
    // TODO
    return false;
}

bool emberAfEnergyManagementClusterCancelPowerAdjustRequestCallback(
    app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
    const Commands::CancelPowerAdjustRequest::DecodableType & commandData)
{
    // TODO
    return false;
}

bool emberAfEnergyManagementClusterStartTimeAdjustRequestCallback(
    app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
    const Commands::StartTimeAdjustRequest::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEnergyManagementClusterPauseRequestCallback(app::CommandHandler * commandObj,
                                                        const app::ConcreteCommandPath & commandPath,
                                                        const Commands::PauseRequest::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEnergyManagementClusterResumeRequestCallback(app::CommandHandler * commandObj,
                                                         const app::ConcreteCommandPath & commandPath,
                                                         const Commands::ResumeRequest::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEnergyManagementClusterModifyPowerForecastRequestCallback(
    app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
    const Commands::ModifyPowerForecastRequest::DecodableType & commandData)
{
    // TODO
    return false;
}
bool emberAfEnergyManagementClusterRequestLimitBasedPowerForecastCallback(
    app::CommandHandler * commandObj, const app::ConcreteCommandPath & commandPath,
    const Commands::RequestLimitBasedPowerForecast::DecodableType & commandData)
{
    // TODO
    return false;
}
