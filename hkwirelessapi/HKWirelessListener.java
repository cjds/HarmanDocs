package com.harman.hkwirelessapi;

public abstract interface HKWirelessListener
{
  public abstract void onDeviceStateUpdated(long paramLong, int paramInt);
  
  public abstract void onPlaybackStateChanged(int paramInt);
  
  public abstract void onVolumeLevelChanged(long paramLong, int paramInt1, int paramInt2);
  
  public abstract void onPlayEnded();
  
  public abstract void onPlaybackTimeChanged(int paramInt);
  
  public abstract void onErrorOccurred(int paramInt, String paramString);
}


/* Location:              C:\Users\Carl Saldanha\Documents\projects\HARMAN\lib\HKWirelessHD.jar!\com\harman\hkwirelessapi\HKWirelessListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */