<div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">选择标签</h4>
      </div>
      <div class="modal-body">
        <#list letters as letter>  
<h3>${letter}</h3>
<p>
   <#list tags as tag>
   <#if tag.cnSpell!='' && tag.cnSpell?substring(0,1)?upper_case==letter>
   <a class="btn btn-link" href="${rc.contextPath}/${tag.name}" >${tag.name}</a>
   </#if>
   </#list> 
</p>
</#list>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->