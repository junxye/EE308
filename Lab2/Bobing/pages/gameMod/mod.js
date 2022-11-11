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

  SkipSingle:function(){
    wx.navigateTo({
      url: '../single/single',
    })
  },

  SkipMulti:function(){
    wx.navigateTo({
      url: '../multi/multi',
    })
  },
  

})

