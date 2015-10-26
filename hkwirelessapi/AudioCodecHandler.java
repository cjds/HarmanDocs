package com.harman.hkwirelessapi;

public class AudioCodecHandler
{
  private PcmCodec m_pcm = new PcmCodec();
  
  /**
   * @CARL Why is this method here if it's empty
   */
  void PcmCodecAPI() {}
   
  /**
   * This method plays a CAF (Core Audio Format) file.
   * CAF is  a container for many differet audio formats, including MP3, FLAC, ACC, OGG and M4A
   * @param paramString1 url an absolute location to the base location of the audio file
   * @param paramString2 songname This is the name of the song as gieven to the speaker, so that the speaker knows the name
   * @param paramBoolean @CARL I think this refers to playing but I'm not sure
   * @CARL no idea what @return should be in this case. It seems to return some kind of boolean. Assumed yes it can play or no it can't
   * @see the song will play on the speaker
   */
  public boolean playCAF(String paramString1, String paramString2, boolean paramBoolean)
  {
    int i = 0;
    if (paramBoolean) {
      i = a.b();
    }
    return this.m_pcm.a(paramString1, i); }
  
  /**
   * This method plays a CAF file from a certain time after its start
   * @param paramString1 url an absolute location to the base location of the audio file
   * @param paramString2 songname This is the name of the song as gieven to the speaker, so that the speaker knows the name
   * @param paramInt timeFromStart @CARD I think this is the time in milliseconds from the start of the file
   * @return NO IDEA @CARL
   * @see  The speaker will play a song from a particular time
   */
  public boolean playCAFFromCertainTime(String paramString1, String paramString2, int paramInt) { return this.m_pcm.a(paramString1, paramInt); }
  
  /**
   * This method pauses the music. @CARL I have no idea what the difference between pause and stop is. Because there is no play method 
   * that does not take a parameter (as in no method for continue from pause)
   * @Carl WHAT DOES THIS DO!!!!! running a method b() makes no sense
   * @see The music will stop playing
   */
  public void pause() { this.m_pcm.b(); }
  
  /**
   * This method stops the music.  @CARL After testing I noticed that it takes a few miliseconds longer than pause()
   * @Carl running a method c() does not mean anything
   * @see The music will stop playing
   */
  public void stop() {
    this.m_pcm.c();
  }
  
  /**
   * This method fetchess the length of the song that is currently playing
   * @return  The duration of the current song in milliseconds in a Long
   */
  public long getDuation() { return this.m_pcm.a(); }
  
  /**
   * This method is designed for playing WAV files.
   * @CARL why is this method kept separately from the CAF play. Also, if this method does not take a string for the title why does the other one
   * @param paramString The location of the song relative to he base location of the file system
   * @return a boolean indicating whether playing this was successful
   * @see  the speaker will start playing the song
   */
  public boolean playWAV(String paramString)
  {
    return this.m_pcm.a(paramString);
  }

  /**
   * This method checks if the speaker is currently playing the song
   * @return boolean which is true if the song is playing
   */
  public boolean isPlaying() {
    return this.m_pcm.IsPlaying();
  }
  
  /**
   * Checks if the player is currently initializing, playing, preparing (@CARL What the does this mean), paused or stopped (@CARL again what is the difference if there is no play())
   * @return One of the following states EPlayerState_Init,  EPlayerState_Play,  EPlayerState_Preparing,  EPlayerState_Pause,  EPlayerState_Stop
   */
  public HKPlayerState getPlayerState() { return HKPlayerState.EPlayerState_Init; }

  /**
   * Changes the volume of every speaker connected to the WiFi
   * @CARL What's the maximum and minimum I can set the volume to. There's no range specified
   * @param paramInt The volume you want to set
   * @see The speaker volume will increase or decrease
   */
  public void setVolumeAll(int paramInt)
  {
    this.m_pcm.SetVolumeAll(paramInt);
  }


  /**
   * Changes the volume of a particular speaker connected to the WiFi specified in the first parameter
   * @param paramLong The ID of the speaker which can be found from the DeviceObject
   * @param paramInt The volume you want to set   * 
   * @see The speaker volume will increase or decrease
   */
  public void setVolumeDevice(long paramLong, int paramInt) {
    this.m_pcm.SetDeviceVolume(paramLong, paramInt);
  }
  

  /**
   * Gets the volume of all the speakers
   * @CARL What does this mean when speakers have multiple volumes
   * @return The volume of the speaker
   */
  public int getVolume() {
    return this.m_pcm.GetVolume();
  }

  /**
   * Gets the volume of a particular device specified by the devie ID
   * @param  paramLong The DeviceId of the speaker which can be found from the DeviceObject
   * @return The volume of the speaker
   */
  public int getDeviceVolume(long paramLong) {
    return this.m_pcm.GetDeviceVolume(paramLong);
  }
  
  /**
   * Returns the maximum volume level that is usable
   * @return The maximum volume of the speaker
   */
  public int getMaximumVolumeLevel() {
    return this.m_pcm.GetMaximumVolumeLevel();
  }
}


/* Location:              C:\Users\Carl Saldanha\Documents\projects\HARMAN\lib\HKWirelessHD.jar!\com\harman\hkwirelessapi\AudioCodecHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */