/*     */ package com.harman.hkwirelessapi;
/*     */ 
/*     */ import android.media.MediaCodec;
/*     */ import android.media.MediaCodec.BufferInfo;
/*     */ import android.media.MediaExtractor;
/*     */ import android.media.MediaFormat;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.os.Message;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PcmCodec
/*     */ {
/*  23 */   private static int m = 2000;
/*     */   
/*     */   MediaCodec a;
/*     */   
/*     */   MediaExtractor b;
/*     */   
/*     */   MediaCodec.BufferInfo c;
/*     */   
/*     */   Boolean d;
/*     */   Boolean e;
/*     */   MediaFormat f;
/*     */   ByteBuffer[] g;
/*     */   ByteBuffer[] h;
/*  36 */   private static int n = 44100;
/*     */   long i;
/*     */   long j;
/*     */   boolean k;
/*     */   private a o;
/*     */   boolean l;
/*     */   
/*     */   public PcmCodec()
/*     */   {
/*  25 */     this.a = null;
/*  26 */     this.b = null;
/*  27 */     this.c = null;
/*     */     
/*  29 */     this.d = Boolean.valueOf(false);
/*  30 */     this.e = Boolean.valueOf(false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */     this.i = 0L;
/*  39 */     this.j = 0L;
/*  40 */     this.k = false;
/*  41 */     this.o = null;
/*  42 */     this.l = true;
/*     */   }
/*     */   
/*     */ 
/*     */   public long a()
/*     */   {
/*  48 */     return this.j;
/*     */   }
/*     */   
/*     */   public synchronized boolean a(String paramString, int paramInt) {
/*  52 */     a.a("play start :" + paramString + " startTime :" + paramInt);
/*     */     
/*  54 */     if (!c(paramString)) {
/*  55 */       this.l = true;
/*  56 */       a.b("file not exist");
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     String str1 = paramString.trim();
/*  61 */     String str2 = str1.substring(str1.indexOf(".") + 1);
/*  62 */     str2 = str2.toLowerCase();
/*  63 */     if ((!str2.equalsIgnoreCase("mp3")) && (!str2.equalsIgnoreCase("wav")) && 
/*  64 */       (!str2.equalsIgnoreCase("flac")) && (!str2.equalsIgnoreCase("acc")) && 
/*  65 */       (!str2.equalsIgnoreCase("m4a")) && (!str2.equalsIgnoreCase("ogg"))) {
/*  66 */       this.l = true;
/*  67 */       g();
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     if (this.k) {
/*  72 */       c();
/*     */     }
/*     */     
/*  75 */     while (!this.l) {
/*     */       try {
/*  77 */         a.a("waiting thread exit :" + this.k);
/*  78 */         Thread.sleep(500L);
/*     */       } catch (InterruptedException localInterruptedException) {
/*  80 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */     }
/*  83 */     this.l = false;
/*     */     
/*  85 */     boolean bool = b(paramString);
/*  86 */     if (!bool) {
/*  87 */       this.l = true;
/*  88 */       a.b("configAudioPlayer fail");
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     this.k = true;
/*  93 */     a.a(true);
/*     */     
/*  95 */     if (paramInt != 0) {
/*  96 */       a(paramInt * 1000000);
/*  97 */       if (!PlayAtTime(paramInt)) {
/*  98 */         this.l = true;
/*  99 */         a.a("PlayAtTime error");
/* 100 */         return false;
/*     */       }
/*     */     }
/* 103 */     else if (!Play()) {
/* 104 */       this.l = true;
/* 105 */       a.a("play error");
/* 106 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 110 */     if (this.o == null) {
/* 111 */       this.o = new a();
/*     */     }
/* 113 */     new Thread(this.o).start();
/*     */     
/* 115 */     a.a("play end");
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   private synchronized boolean b(String paramString) {
/* 120 */     a.a("configAudioPlayer start");
/*     */     
/* 122 */     if (this.b == null) {
/* 123 */       this.b = new MediaExtractor();
/*     */     } else {
/* 125 */       this.b.release();
/* 126 */       this.b = null;
/* 127 */       this.b = new MediaExtractor();
/*     */     }
/*     */     try {
/* 130 */       this.b.setDataSource(paramString);
/*     */     } catch (IOException localIOException1) {
/* 132 */       localIOException1.printStackTrace();
/* 133 */       return false;
/*     */     }
/*     */     
/* 136 */     int i1 = this.b.getTrackCount();
/*     */     try {
/* 138 */       this.f = this.b.getTrackFormat(0);
/*     */     } catch (RuntimeException localRuntimeException) {
/* 140 */       localRuntimeException.printStackTrace();
/* 141 */       return false;
/*     */     }
/*     */     
/* 144 */     String str1 = this.f.getString("mime");
/* 145 */     int i2 = this.f.getInteger("sample-rate");
/* 146 */     this.j = this.f.getLong("durationUs");
/* 147 */     int i3 = this.f.getInteger("channel-count");
/*     */     
/*     */ 
/* 150 */     a.a("===========================");
/* 151 */     a.a("url " + paramString);
/* 152 */     a.a("mime type : " + str1);
/* 153 */     a.a("numTracks : " + i1);
/* 154 */     a.a("sample rate : " + i2);
/* 155 */     a.a("channelCnt : " + i3);
/* 156 */     a.a("duration : " + this.j);
/* 157 */     a.a("===========================");
/*     */     
/* 159 */     if (i2 < n) {
/* 160 */       a.b("sample rate : " + i2 + "< 44100");
/* 161 */       g();
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     if (this.a != null) {
/* 166 */       this.a.release();
/* 167 */       this.a = null;
/*     */     }
/*     */     try {
/* 170 */       this.a = MediaCodec.createDecoderByType(str1);
/*     */     } catch (IOException localIOException2) {
/* 172 */       localIOException2.printStackTrace();
/* 173 */       return false;
/*     */     }
/*     */     
/* 176 */     this.a.configure(this.f, null, null, 0);
/* 177 */     this.a.start();
/*     */     
/* 179 */     this.g = this.a.getInputBuffers();
/* 180 */     this.h = this.a.getOutputBuffers();
/*     */     
/* 182 */     this.b.selectTrack(0);
/*     */     
/* 184 */     if (this.c == null) {
/* 185 */       this.c = new MediaCodec.BufferInfo();
/*     */     } else {
/* 187 */       this.c = null;
/* 188 */       this.c = new MediaCodec.BufferInfo();
/*     */     }
/*     */     
/* 191 */     String str2 = paramString.trim();
/* 192 */     String str3 = str2.substring(str2.lastIndexOf("/") + 1);
/* 193 */     String str4 = str3.substring(0, str3.lastIndexOf("."));
/* 194 */     SetAudioFile(paramString, str4, this.j, 16, i2, i3);
/*     */     
/* 196 */     a.a("configAudioPlayer end");
/* 197 */     return true;
/*     */   }
/*     */   
/*     */   public void b()
/*     */   {
/* 202 */     a.a("pause start");
/* 203 */     this.k = false;
/* 204 */     Pause();
/* 205 */     a.a("pause end");
/*     */   }
/*     */   
/*     */   public void c() {
/* 209 */     a.a("stop");
/* 210 */     this.k = false;
/* 211 */     Stop();
/* 212 */     a.a("stop end");
/*     */   }
/*     */   
/*     */ 
/*     */   private synchronized void a(long paramLong)
/*     */   {
/* 218 */     this.b.seekTo(paramLong, 2);
/*     */     
/* 220 */     this.d = Boolean.valueOf(false);
/* 221 */     this.e = Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */   private void d() {
/* 225 */     this.d = Boolean.valueOf(false);
/* 226 */     this.e = Boolean.valueOf(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   class a
/*     */     implements Runnable
/*     */   {
/*     */     a() {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public synchronized void run()
/*     */     {
/* 246 */       a.a("m_Player Thread");
/*     */       
/* 248 */       PcmCodec.this.k = true;
/* 249 */       while (!Thread.interrupted())
/*     */       {
/* 251 */         if ((!PcmCodec.this.k) || (!a.a())) {
/* 252 */           a.a("Thread stop");
/* 253 */           break;
/*     */         }
/*     */         
/* 256 */         PcmCodec.a(PcmCodec.this);
/* 257 */         boolean bool = PcmCodec.b(PcmCodec.this);
/* 258 */         if (!bool) {
/* 259 */           PcmCodec.this.k = false;
/* 260 */           break;
/*     */         }
/*     */         
/*     */ 
/* 264 */         if ((PcmCodec.this.c.flags & 0x4) != 0) {
/* 265 */           a.a("OutputBuffer BUFFER_FLAG_END_OF_STREAM");
/* 266 */           PcmCodec.this.k = false;
/* 267 */           break;
/*     */         }
/*     */       }
/* 270 */       PcmCodec.c(PcmCodec.this);
/* 271 */       a.a("thread end");
/* 272 */       PcmCodec.this.End();
/* 273 */       PcmCodec.this.l = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private int e()
/*     */   {
/* 280 */     int i1 = this.a.dequeueInputBuffer(m);
/*     */     
/* 282 */     if ((!this.k) || (!a.a())) {
/* 283 */       return -1;
/*     */     }
/*     */     
/* 286 */     if (i1 >= 0) {
/* 287 */       ByteBuffer localByteBuffer = this.g[i1];
/* 288 */       if (this.b == null) {
/* 289 */         return -1;
/*     */       }
/* 291 */       int i2 = this.b.readSampleData(localByteBuffer, 0);
/* 292 */       if (i2 < 0) {
/* 293 */         a.a("Saw input end of stream!");
/* 294 */         this.d = Boolean.valueOf(true);
/* 295 */         i2 = 0;
/*     */       } else {
/* 297 */         if (this.b == null) {
/* 298 */           return -1;
/*     */         }
/* 300 */         this.i = this.b.getSampleTime();
/*     */       }
/*     */       
/*     */ 
/* 304 */       this.a.queueInputBuffer(i1, 0, i2, this.i, this.d
/*     */       
/*     */ 
/*     */ 
/* 308 */         .booleanValue() ? 4 : 0);
/* 309 */       if (!this.d.booleanValue()) {
/* 310 */         this.b.advance();
/*     */       } else {
/* 312 */         a.a("sawInputEOS");
/*     */       }
/*     */     } else {
/* 315 */       a.a("inputBufIndex = " + i1);
/*     */     }
/* 317 */     return i1;
/*     */   }
/*     */   
/*     */   private boolean f()
/*     */   {
/* 322 */     int i1 = this.a.dequeueOutputBuffer(this.c, m);
/*     */     
/* 324 */     if ((!this.k) || (!a.a())) {
/* 325 */       return false;
/*     */     }
/*     */     
/* 328 */     if (i1 >= 0) {
/* 329 */       int i2 = i1;
/* 330 */       ByteBuffer localByteBuffer = this.h[i2];
/*     */       
/* 332 */       byte[] arrayOfByte = new byte[this.c.size];
/* 333 */       localByteBuffer.get(arrayOfByte);
/* 334 */       localByteBuffer.clear();
/*     */       
/* 336 */       if (arrayOfByte.length > 0)
/*     */       {
/* 338 */         if (!SampleBuffer(arrayOfByte)) {
/* 339 */           return false;
/*     */         }
/*     */       }
/* 342 */       this.a.releaseOutputBuffer(i2, false);
/*     */       
/* 344 */       if ((this.c.flags & 0x4) != 0) {
/* 345 */         this.e = Boolean.valueOf(true);
/*     */       }
/* 347 */     } else if (i1 == -3) {
/* 348 */       this.h = this.a.getOutputBuffers();
/* 349 */       a.a("INFO_OUTPUT_BUFFERS_CHANGED");
/* 350 */     } else if (i1 == -2) {
/* 351 */       MediaFormat localMediaFormat = this.a.getOutputFormat();
/* 352 */       a.a("Output format has changed to " + localMediaFormat);
/*     */     }
/*     */     else {
/* 355 */       a.a("ret = " + i1);
/*     */     }
/* 357 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized boolean a(String paramString)
/*     */   {
/* 362 */     a.a("playWAV start :" + paramString);
/*     */     
/* 364 */     if (!c(paramString)) {
/* 365 */       a.b("file not exist");
/* 366 */       return false;
/*     */     }
/*     */     
/* 369 */     String str1 = paramString.trim();
/* 370 */     String str2 = str1.substring(str1.lastIndexOf(".") + 1);
/* 371 */     str2 = str2.toLowerCase();
/* 372 */     if (!str2.equalsIgnoreCase("wav")) {
/* 373 */       a.b("file not wav file");
/* 374 */       g();
/* 375 */       return false;
/*     */     }
/*     */     
/* 378 */     MediaExtractor localMediaExtractor = null;
/*     */     try {
/* 380 */       localMediaExtractor = new MediaExtractor();
/*     */     } catch (RuntimeException localRuntimeException1) {
/* 382 */       localRuntimeException1.printStackTrace();
/* 383 */       a.b("MediaExtractor error");
/*     */     }
/*     */     try
/*     */     {
/* 387 */       localMediaExtractor.setDataSource(paramString);
/*     */     } catch (IOException localIOException) {
/* 389 */       localIOException.printStackTrace();
/* 390 */       a.b("setDataSource error");
/* 391 */       return false;
/*     */     }
/*     */     
/* 394 */     MediaFormat localMediaFormat = null;
/* 395 */     int i1 = localMediaExtractor.getTrackCount();
/* 396 */     if (i1 > 0) {
/*     */       try {
/* 398 */         localMediaFormat = localMediaExtractor.getTrackFormat(0);
/*     */       } catch (RuntimeException localRuntimeException2) {
/* 400 */         localRuntimeException2.printStackTrace();
/* 401 */         a.b("getTrackFormat error");
/* 402 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 406 */       int i2 = localMediaFormat.getInteger("sample-rate");
/*     */       
/* 408 */       this.j = localMediaFormat.getLong("durationUs");
/*     */       
/* 410 */       if (i2 < n) {
/* 411 */         g();
/* 412 */         return false;
/*     */       }
/*     */       
/* 415 */       return PlayWav(paramString);
/*     */     }
/* 417 */     return false;
/*     */   }
/*     */   
/*     */   private boolean c(String paramString)
/*     */   {
/*     */     try {
/* 423 */       File localFile = new File(paramString);
/* 424 */       if (!localFile.exists()) {
/* 425 */         return false;
/*     */       }
/*     */     } catch (Exception localException) {
/* 428 */       return false;
/*     */     }
/* 430 */     return true;
/*     */   }
/*     */   
/*     */   private void g() {
/* 434 */     a.a("formatNotSupportMsg start");
/* 435 */     if (a.a != null) {
/* 436 */       Message localMessage = new Message();
/* 437 */       localMessage.what = 1;
/* 438 */       localMessage.arg1 = HKErrorCode.ERROR_MEDIA_UNSUPPORTED.ordinal();
/* 439 */       Bundle localBundle = new Bundle();
/* 440 */       localBundle.putString("errorMesg", "media format is not supported !");
/* 441 */       localMessage.setData(localBundle);
/* 442 */       a.a.sendMessage(localMessage);
/*     */     }
/* 444 */     a.a("formatNotSupportMsg end");
/*     */   }
/*     */   
/*     */   public native void SetAudioFile(String paramString1, String paramString2, long paramLong, int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public native boolean Play();
/*     */   
/*     */   public native boolean PlayAtTime(long paramLong);
/*     */   
/*     */   public native boolean SampleBuffer(byte[] paramArrayOfByte);
/*     */   
/*     */   public native void Pause();
/*     */   
/*     */   public native void Stop();
/*     */   
/*     */   public native void End();
/*     */   
/*     */   public native boolean PlayWav(String paramString);
/*     */   
/*     */   public native void SetDeviceVolume(long paramLong, int paramInt);
/*     */   
/*     */   public native void SetVolumeAll(int paramInt);
/*     */   
/*     */   public native int GetVolume();
/*     */   
/*     */   public native int GetDeviceVolume(long paramLong);
/*     */   
/*     */   public native int GetMaximumVolumeLevel();
/*     */   
/*     */   public native boolean IsPlaying();
/*     */ }


/* Location:              C:\Users\Carl Saldanha\Documents\projects\HARMAN\lib\HKWirelessHD.jar!\com\harman\hkwirelessapi\PcmCodec.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */