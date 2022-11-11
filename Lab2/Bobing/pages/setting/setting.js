Page({

  /**
   * 页面的初始数据
   */
  data: {
    tol: 100,
    music: 100,
    effect: 100,
  },
/**
  methods: {
    slider1change: Function(e),
      tol = e.detail.value,
  },

   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      wx.hideTabBar({
        animation: true,
      })
  },
  onLoad() {

  },
  onShow() {

  },

  SkipHome:function(){
    wx.navigateTo({
      url: '../home/home',
    })
  },
  

})

