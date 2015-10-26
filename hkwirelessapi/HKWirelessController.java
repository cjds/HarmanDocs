package com.harman.hkwirelessapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;




public class HKWirelessController
{
  private HKWirelessListener a = null;
  
  public HKWirelessController() {
    a.a = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage) {
        switch (paramAnonymousMessage.what)
        {
        case 1: 
          String str = paramAnonymousMessage.getData().getString("errorMesg");
          a.a("The handler thread id = " + Thread.currentThread().getId() + " " + str + " " + paramAnonymousMessage.arg1);
          if (HKWirelessController.a(HKWirelessController.this) != null) {
            HKWirelessController.a(HKWirelessController.this).onErrorOccurred(HKErrorCode.ERROR_MEDIA_UNSUPPORTED.ordinal(), str);
          }
          break;
        }
      }
    };
  }
  
  /**
   * This sets which HKWirelessListener will be used for the sessuib created. This is an interface that can listen to changes in 
   * the volume and the state of the HK Device
   * @param paramHKWirelessListener This is a listener for the HK Wireless
   */
  public void a(HKWirelessListener paramHKWirelessListener) {
    this.a = paramHKWirelessListener;
  }
  
  /**
   * This is called when the Device state is updated from starting up, shutting down etc.
   * @CARL I guess the params refer to the old state and the new but I can;t
   * tell why they would be two different types
   */
  public void callbackDeviceStateUpdated(long paramLong, int paramInt) {
    a.a("callbackDeviceStateUpdated");
    if (this.a != null)
      this.a.onDeviceStateUpdated(paramLong, paramInt);
  }
  
  /**
   * This is called when the playback state is changed. From paused, to play, to stop and vice versa
   * @param paramInt The new state that the playbck has changed into
   */
  public void callbackPlaybackStateChanged(int paramInt) {
    a.a("callbackPlaybackStateChanged : " + paramInt);
    if (this.a != null) {
      this.a.onPlaybackStateChanged(paramInt);
    }
    if (paramInt == HKPlayerState.EPlayerState_Stop.ordinal())
      a.a(false);
  }
  
  /**
   * This is called when the callback volume level has been changed
   * @param paramLong The device ID whose volume has changed
   * @param paramInt1 The original volume of the device
   * @param paramInt2 The new volume of the device
   */
  public void callbackVolumeLevelChanged(long paramLong, int paramInt1, int paramInt2) {
    a.a("callbackVolumeLevelChanged : " + paramInt1);
    if (this.a != null)
      this.a.onVolumeLevelChanged(paramLong, paramInt1, paramInt2);
  }
  
  /**
   * This callback occurs when playing of a song ends
   */
  public void callbackPlayEnded() {
    a.a("callbackPlayEnded");
    if (this.a != null)
      this.a.onPlayEnded();
  }
  
  /**
   * @CARL not sure what callback this is supposed to be
   * @param The time which is currently playing
   */
  public void callbackPlaybackTimeChanged(int paramInt) {
    a.a("callbackPlaybackTimeChanged :" + paramInt);
    a.a(paramInt);
    if (this.a != null)
      this.a.onPlaybackTimeChanged(paramInt);
  }
  

  /**
   * This callback is called when an error occurs in the system
   * @param paramInt the error code id @CARL I guess????
   * @param paramString The string of the error
   */
  public void callbackErrorOccurred(int paramInt, String paramString) {
    a.a("callbackErrorOccurred");
    if (this.a != null)
      this.a.onErrorOccurred(paramInt, paramString); }
  
  /**
   * Native method used for starting up the HK Wireless
   * @CARL I have no idea why we are passing a String to this
   * @return A random integer @CARL
   */
  public native int InitHKWireless(String paramString);
  
  /**
   * Native method to check if the HK is currently initialized
   * @return boolean True indicates that it is indeed initialized
   */
  public native boolean IsInitialized();
  
  /**
   * This method refreshes the device information so you can check if there are n
   * any new speakers or if the information was changed by another app
   */
  public native void RefreshDeviceInfoOnce();
  
  /**
   * This method is called to start refreshing the device information. 
   * @CARL When is this supposed to be used 
   */
  public native void StartRefreshDeviceInfo();
  
  /**
   * This method is called if you want to prevent the device from refreshing 
   * the device information
   * @CARL Why would you ever want to do this
   */
  public native void StopRefreshDeviceInfo();
  
  /**
   * This method allows you to add a device to the session. The ID can be acquired from
   * the DeviceObj. Once a device is in a session, the play and pause commands will work on it
   * @CARL I'm assuming this is true
   * @param The long ID of the device that you are adding to the session
   * @return It returns whether the device was successfully added or not
   */
  public native boolean AddDeviceToSession(long paramLong);
  
  /**
   * This method allows you to remove a device from the sesison. The ID can be acquired from 
   * the DeviceObj.
   * @param The long ID of the device you are removing 
   * @return It returns true if the device is successfully removed
   */
  public native boolean RemoveDeviceFromSession(long paramLong);
  
  /**
   * @CARL Counts the number of groups I guess. I can get the count but there isn't a method for get all groups
   * @return the number of groups
   */
  public native int GetGroupCount();
  
  /**
   * Counts the number of devices in a particular group
   * @CARL What is a group index and how can I find it 
   * @return the number of devices in a group
   */
  public native int GetDeviceCountInGroupIndex(int paramInt);
  
/**
* Counts the total number of devices
* @return the number of devices
*/
  public native int GetDeviceCount();
  
  /**
   * @CARL Not A CLUE. No use case or method presented anywhere. What is Table anyway
   */
  public native DeviceObj GetDeviceInfoFromTable(int paramInt1, int paramInt2);
  
   /**
    * Returns all the information about a device based on its index
   * @CARL What is an index. How many are there. Where can I get a list of indices
   */
  public native DeviceObj GetDeviceInfoByIndex(int paramInt);
  
  /**
   * Get a DeviceObj of the device by specifying its Long ID
   * @param The ID of the particular device
   * @return A DeviceObj with list 
   */ 
  public native DeviceObj FindDeviceFromList(long paramLong);

  /**
   * Get a GroupObj of the group in which a device is in
   * @param The ID of the particular device
   * @return The GroupObj of a particular group
   */ 
  public native GroupObj FindGroupWithDeviceId(long paramLong);

  /**
   * @CARL I have no idea what the index means as usual
   * @param the index of the group
   * @return The GroupObj of that particular group
   */
  public native GroupObj GetGroupByIndex(int paramInt);

  /**
   * Given the long ID of a group, it returns a device 
   * @param The group id of the device 
   * @return The GroupObj of the group
   */
  public native GroupObj GetGroupById(long paramLong);

  /**
   * Check whether a particular device is active
   * @param paramLong The long ID of the Device
   * @return If the device is active, this returns True
   */
  public native boolean IsDeviceActive(long paramLong);

/**
 * Removes a device from a particular group 
 * @CARL Is it group first or device first. We need proper variable names
 * @param paramLong1 The group to be removed from
 * @param paramLong2 The device ID to be removed
 */
  public native void RemoveDeviceFromGroup(long paramLong1, long paramLong2);

  /**
   * A group name is found for the object
   * @param  The index which responds to a group 
   * @return The name of the group eg. Living Room, Bathroom etc.
   */
  public native String GetGroupNameByIndex(int paramInt);

  /**
   * Get the Long group ID from the Index 
   * @CARL maybe it should be the other way around, how do you know what 
   * devices to use
   * @param  The index which responds to a group
   * @return The Long ID of a particular group
   */
  public native long GetGroupIdByIndex(int paramInt);

/**
 * Give a name to a particular Device
 * @param The long ID of the device to be given the name
 * @param The name of the device
 */
  public native void SetDeviceName(long paramLong, String paramString);

  /**
   * Set the name for the group
   * @param The long ID of the group
   * @param The String is the group name that it will be 
   */
  public native void SetDeviceGroupName(long paramLong, String paramString);

  /**
   * @CARL I have NO idea what this does or what Params it takes
   */
  public native void SetDeviceRole(long paramLong, int paramInt);

  /**
   * Get the number of active devices
   * @return the number of active devices
   */
  public native int GetActiveDeviceCount();

  /**
   * Get the number of active groups
   * @return the number of active groups
   */
  public native int GetActiveGroupCount();

  /**
   * @CARL Not really sure what this does
   * @param The ID of the device
   */
  public native void RefreshDeviceWiFiSignal(long paramLong);

  /**
   * Returns the signal strength of the WiFi signal
   * @param NOT REALLY SURE @CARL
   * @return NOT REALLY SURE @CARL
   */
  public native int GetWifiSignalStrengthType(int paramInt);
  static { System.loadLibrary("HKWirelessHD"); }
}


/* Location:              C:\Users\Carl Saldanha\Documents\projects\HARMAN\lib\HKWirelessHD.jar!\com\harman\hkwirelessapi\HKWirelessController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */2                                               