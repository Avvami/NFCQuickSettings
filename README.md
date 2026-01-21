<div align="center">
  <img width="160" height="160" alt="Image" src="https://github.com/user-attachments/assets/18e5cb86-2347-4c7a-8a5c-29463fbe2d8b" align="center" />
  
  # NFC Quick Settings
</div>

This is a lightweight, unofficial fork of [pcolby/nfc-quick-settings](https://github.com/pcolby/nfc-quick-settings). It adds a tile to the Quick Settings panel to control NFC.

## Features
*   **Basic Mode:** Tapping the tile opens system NFC settings.
*   **Advanced Mode:** Tapping the tile toggles NFC on/off instantly (requires ADB permission).

## Advanced Mode Setup
To enable direct toggling without opening settings, grant the `WRITE_SECURE_SETTINGS` permission via ADB.

Run this command (replace `com.personal.nfcquicksettings` with your app's package name):
```sh
adb shell pm grant com.personal.nfcquicksettings android.permission.WRITE_SECURE_SETTINGS
```
To revert to basic mode:
```sh
adb shell pm revoke com.personal.nfcquicksettings android.permission.WRITE_SECURE_SETTINGS
```
