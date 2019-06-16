package com.moko.support.task;

import com.moko.support.callback.MokoOrderTaskCallback;
import com.moko.support.entity.ConfigKeyEnum;
import com.moko.support.entity.OrderEnum;
import com.moko.support.entity.OrderType;
import com.moko.support.utils.MokoUtils;

/**
 * @Date 2018/1/20
 * @Author wenzheng.liu
 * @Description
 * @ClassPath com.moko.support.task.WriteConfigTask
 */
public class WriteConfigTask extends OrderTask {
    public byte[] data;

    public WriteConfigTask(MokoOrderTaskCallback callback) {
        super(OrderType.writeConfig, OrderEnum.WRITE_CONFIG, callback, OrderTask.RESPONSE_TYPE_WRITE_NO_RESPONSE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(ConfigKeyEnum key) {
        switch (key) {
//            case GET_SLOT_TYPE:
            case GET_DEVICE_MAC:
//            case GET_DEVICE_NAME:
            case GET_CONNECTABLE:
            case GET_IBEACON_UUID:
            case GET_IBEACON_INFO:
            case SET_CLOSE:
            case GET_AXIX_PARAMS:
            case GET_TH_PERIOD:
            case GET_STORAGE_CONDITION:
            case GET_DEVICE_TIME:
                createGetConfigData(key.getConfigKey());
                break;
        }
    }

    private void createGetConfigData(int configKey) {
        data = new byte[]{(byte) 0xEA, (byte) configKey, (byte) 0x00, (byte) 0x00};
    }

    public void setiBeaconData(int major, int minor, int advTxPower) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_IBEACON_INFO.getConfigKey()) + "0005"
                + String.format("%04X", major) + String.format("%04X", minor) + MokoUtils.int2HexString(Math.abs(advTxPower));
        data = MokoUtils.hex2bytes(value);
    }

    public void setiBeaconUUID(String uuidHex) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_IBEACON_UUID.getConfigKey()) + "0010"
                + uuidHex;
        data = MokoUtils.hex2bytes(value);
    }

    public void setConneactable(boolean isConnectable) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_CONNECTABLE.getConfigKey()) + "0001"
                + (isConnectable ? "01" : "00");
        data = MokoUtils.hex2bytes(value);
    }

    public void setAxisParams(int rate, int scale, int sensitivity) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_AXIX_PARAMS.getConfigKey()) + "0003"
                + String.format("%02X", rate) + String.format("%02X", scale) + String.format("%02X", sensitivity);
        data = MokoUtils.hex2bytes(value);
    }

    public void setTHPriod(int period) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_TH_PERIOD.getConfigKey()) + "0002"
                + String.format("%04X", period);
        data = MokoUtils.hex2bytes(value);
    }

    public void setStorageCondition(int storageType, String storageData) {
        String value = "00";
        switch (storageType) {
            case 0:
                value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_STORAGE_CONDITION.getConfigKey()) + "0003"
                        + String.format("%02X", storageType) + storageData;
                break;
            case 1:
                value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_STORAGE_CONDITION.getConfigKey()) + "0003"
                        + String.format("%02X", storageType) + storageData;
                break;
            case 2:
                value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_STORAGE_CONDITION.getConfigKey()) + "0005"
                        + String.format("%02X", storageType) + storageData;
                break;
            case 3:
                value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_STORAGE_CONDITION.getConfigKey()) + "0002"
                        + String.format("%02X", storageType) + storageData;
                break;
        }

        data = MokoUtils.hex2bytes(value);
    }

    public void setDeviceTime(int year, int month, int day, int hour, int minute, int second) {
        String value = "EA" + MokoUtils.int2HexString(ConfigKeyEnum.SET_TH_PERIOD.getConfigKey()) + "0006"
                + String.format("%02X", year) + String.format("%02X", month) + String.format("%02X", day)
                + String.format("%02X", hour) + String.format("%02X", minute) + String.format("%02X", second);
        data = MokoUtils.hex2bytes(value);
    }
}