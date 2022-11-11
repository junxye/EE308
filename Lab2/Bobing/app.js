// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    const bgMusic = wx.getBackgroundAudioManager()//背景音频

    bgMusic.src = "audio/陈军、剑网3 - 枕月人间 (二胡演奏版).mp3"
  },
  globalData: {
    userInfo: null
  }
})
