Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
/**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      wx.hideTabBar({
        animation: true,
      })
  },

  SkipBegin:function(){
    wx.navigateTo({
      url: '../gameMod/mod',
    })
  },

  SkipRule:function(){
    wx.navigateTo({
      url: '../rulePage/rulePage',
    })
  },

  SkipSet:function(){
    wx.navigateTo({
      url: '../setting/setting',
    })
  },

  SkipExit:function(){
    wx.exitMiniProgram({
      success: function() {
        Taro.setStorageSync('protocolUpdateUuid', '');
      },
      fail: function() {
        Taro.setStorageSync('protocolUpdateUuid', '');
      }
  })
  }
  

})

