pacfkage com.harman.hkwirelessapi;

import android.os.Build.VERSION;



/**
 * This is the HKWirelessHandler class, used for Handling an objet of the controller and connecting it to a listener.
 * It also contains several helper methods
 */
public class HKWirelessHandler
{
 /**
  * The HKWirelessController instance automatically generated for its use with the Handler
  */
  private HKWirelessController m_wireless = new HKWirelessController();

  /**
   * A Listener can be attached to this to add methods such as onPlaybackChanged, onVolumeChanged etc.
   */
  private HKWirelessListener m_listener = null;
  
  /**
   * A constant used to indicate success
   */
  public int HKW_INIT_SUCCESS = 0;
  public int HKW_INIT_FAILURE_LICENSE_INVALID = -1;
  public int HKW_INIT_FAILURE_SDK_VERSION_LOW = -2;
  
  /**
   * @CARL This method is useless
   */
  void HKWirelessAPI() {}

  /**
   * Native method used for starting up the HK Wireless
   * @CARL I have no idea why we are passing a String to this
   * @return A random integer @CARL
   */
  public int initializeHKWirelessController(String paramString)
  {
    if (Build.VERSION.SDK_INT < 16) {
      return this.HKW_INIT_FAILURE_SDK_VERSION_LOW;
    }
    return this.m_wireless.InitHKWireless(paramString);
  }

  /**
   * Native method to check if the HK is currently initialized
   * @return boolean True indicates that it is indeed initialized
   */
  public boolean isInitialized() {
    return this.m_wireless.IsInitialized();
  }
  

  /**
   * This is the method that allows you to add an HK Wireless listener to the controller
   * @param Attach a new HKWireless Listener to Listen for changes
   */
  public void registerHKWirelessControllerListener(HKWirelessListener paramHKWirelessListener) {
    this.m_wireless.a(paramHKWirelessListener);
  }
  
   /**
   * This method refreshes the device information so you can check if there are n
   * any new speakers or if the information was changed by another app
   */
  public void refreshDeviceInfoOnce() {
    this.m_wireless.RefreshDeviceInfoOnce();
  }
  
    /**
   * This method is called to start refreshing the device information. 
   * @CARL When is this supposed to be used 
   */
  public void startRefreshDeviceInfo() {
    this.m_wireless.StartRefreshDeviceInfo();
  }
  
  /**
   * This method is called if you want to prevent the device from refreshing 
   * the device information
   * @CARL Why would you ever want to do this
   */
   public void stopRefreshDeviceInfo() {
    this.m_wireless.StopRefreshDeviceInfo();
  }

  /**
   * This method allows you to add a device to the session. The ID can be acquired from
   * the DeviceObj. Once a device is in a session, the play and pause commands will work on it
   * @CARL I'm assuming this is true
   * @param The long ID of the device that you are adding to the session
   * @return It returns whether the device was successfully added or not
   */
   public boolean addDeviceToSession(long paramLong) {
    return this.m_wireless.AddDeviceToSession(paramLong);
  }
  
  /**
   * This method allows you to remove a device from the sesison. The ID can be acquired from 
   * the DeviceObj.
   * @param The long ID of the device you are removing 
   * @return It returns true if the device is successfully removed
   */
  public boolean removeDeviceFromSession(long paramLong) { return this.m_wireless.RemoveDeviceFromSession(paramLong); }
  
  /**
   * @CARL Counts the number of groups I guess. I can get the count but there isn't a method for get all groups
   * @return the number of groups
   */
  public int getGroupCount()
  {
    return this.m_wireless.GetGroupCount();
  }
  
  /**
   * Counts the number of devices in a particular group
   * @CARL What is a group index and how can I find it 
   * @return the number of devices in a group
   */
  public int getDeviceCountInGroupIndex(int paramInt) {
    return this.m_wireless.GetDeviceCountInGroupIndex(paramInt);
  }
  
/**
* Counts the total number of devices
* @return the number of devices
*/
  public int getDeviceCount() {
    return this.m_wireless.GetDeviceCount();
  }
  
  /**
   * @CARL Not A CLUE. No use case or method presented anywhere. What is Table anyway
   */
  public DeviceObj getDeviceInfoFromTable(int paramInt1, int paramInt2) {
    return this.m_wireless.GetDeviceInfoFromTable(paramInt1, paramInt2);
  }
  
   /**
    * Returns all the information about a device based on its index
   * @CARL What is an index. How many are there. Where can I get a list of indices
   */
  public DeviceObj getDeviceInfoByIndex(int paramInt) {
    return this.m_wireless.GetDeviceInfoByIndex(paramInt);
  }
  
  /**
   * Get a GroupObj of the group in which a device is in
   * @param The ID of the particular device
   * @return The GroupObj of a particular group
   */   
  public GroupObj findDeviceGroupWithDeviceId(long paramLong) {
    return this.m_wireless.FindGroupWithDeviceId(paramLong);
  }
  
/**
   * Get a DeviceObj of the device by specifying its Long ID
   * @param The ID of the particular device
   * @return A DeviceObj with list 
   */ 
  public DeviceObj findDeviceFromList(long paramLong) {
    return this.m_wireless.FindDeviceFromList(paramLong);
  }
  
  /**
   * Check whether a particular device is active
   * @param paramLong The long ID of the Device
   * @return If the device is active, this returns True
   */
  public boolean isDeviceActive(long paramLong) {
    return this.m_wireless.IsDeviceActive(paramLong);
  }
  
/**
 * Removes a device from a particular group 
 * @CARL Is it group first or device first. We need proper variable names
 * @param paramLong1 The group to be removed from
 * @param paramLong2 The device ID to be removed
 */
  public void removeDeviceFromGroup(long paramLong1, long paramLong2) {
    this.m_wireless.RemoveDeviceFromGroup(paramLong1, paramLong2);
  }
   /**
   * @CARL I have no idea what the index means as usual
   * @param the index of the group
   * @return The GroupObj of that particular group
   */ 
  public GroupObj getDeviceGroupByIndex(int paramInt) {
    return this.m_wireless.GetGroupByIndex(paramInt);
  }
   /**
   * Given the long ID of a group, it returns a device 
   * @param The group id of the device 
   * @return The GroupObj of the group
   */
 
  public GroupObj getDeviceGroupById(long paramLong) {
    return this.m_wireless.GetGroupById(paramLong);
  }
  
    /**
   * A group name is found for the object
   * @param  The index which responds to a group 
   * @return The name of the group eg. Living Room, Bathroom etc.
   */
  public String getDeviceGroupNameByIndex(int paramInt) {
    return this.m_wireless.GetGroupNameByIndex(paramInt);
  }
  

  /**
   * Get the Long group ID from the Index 
   * @CARL maybe it should be the other way around, how do you know what 
   * devices to use
   * @param  The index which responds to a group
   * @return The Long ID of a particular group
   */
  public long getDeviceGroupIdByIndex(int paramInt) {
    return this.m_wireless.GetGroupIdByIndex(paramInt);
  }
  
/**
 * Give a name to a particular Device
 * @param The long ID of the device to be given the name
 * @param The name of the device
 */
 public void setDeviceName(long paramLong, String paramString) {
    this.m_wireless.SetDeviceName(paramLong, paramString);
  }
  
  /**
   * Set the name for the group
   * @param The long ID of the group
   * @param The String is the group name that it will be 
   */
  public void setDeviceGroupName(long paramLong, String paramString) {
    this.m_wireless.SetDeviceGroupName(paramLong, paramString);
  }
  
  /**
   * @CARL I have NO idea what this does or what Params it takes
   */
  public void setDeviceRole(long paramLong, int paramInt) {
    this.m_wireless.SetDeviceRole(paramLong, paramInt);
  }
  
  /**
   * Get the number of active devices
   * @return the number of active devices
   */
  public int getActiveDeviceCount() {
    return this.m_wireless.GetActiveDeviceCount();
  }
  

  /**
   * Get the number of active groups
   * @return the number of active groups
   */
  public int getActiveGroupCount() {
    return this.m_wireless.GetActiveGroupCount();
  }
  
  /**
   * @CARL Not really sure what this does
   * @param The ID of the device
   */
  public void refreshDeviceWiFiSignal(long paramLong) {
    this.m_wireless.RefreshDeviceWiFiSignal(paramLong);
  }
  
    /**
   * Returns the signal strength of the WiFi signal
   * @param NOT REALLY SURE @CARL
   * @return NOT REALLY SURE @CARL
   */
  public HKWifiSingalStrength getWifiSignalStrengthType(int paramInt) {
    HKWifiSingalStrength localHKWifiSingalStrength = HKWifiSingalStrength.values()[this.m_wireless.GetWifiSignalStrengthType(paramInt)];
    return localHKWifiSingalStrength;
  }
}


/* Location:              C:\Users\Carl Saldanha\Documents\projects\HARMAN\lib\HKWirelessHD.jar!\com\harman\hkwirelessapi\HKWirelessHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */