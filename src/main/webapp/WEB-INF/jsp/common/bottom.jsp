 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
 <div class="bottom">
 <div class="container">
 <p class="text-center">
 <a href="http://weibo.com/339238080" target="_blank">Idiot's Sky</a>
 </p>
 </div>
 </div>
 <script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/js/lazyload.js"></script>
<script type="text/javascript" >
$(function() {
    $("img.lazy").lazyload({
        effect : "fadeIn"
    });
    
    var backToTopTxt = "返回顶部", backToTopEle = $('<div class="backToTop tops"></div>').appendTo($("body"))
    .text(backToTopTxt).attr("title", backToTopTxt).click(function() {
        $("html, body").animate({ scrollTop: 0 }, 520);
}), backToTopFun = function() {
    var st = $(document).scrollTop(), winh = $(window).height();
    (st > 300)? backToTopEle.show(): backToTopEle.hide();    
    //IE6下的定位
    if (!window.XMLHttpRequest) {
        backToTopEle.css("top", st + winh - 60);    
    }
};
$(window).bind("scroll", backToTopFun);
backToTopFun();

});
</script>
  <!-- Modal -->
<div class="modal fade" id="cityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div><!-- /.modal -->
  <!-- Modal -->
<div class="modal fade" id="tagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div><!-- /.modal -->