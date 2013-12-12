<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <H2>Entre com os dados do tipo da conta</H2>
   <f:form commandName="accountType" method="POST" cssClass="form-horizontal" role="form">
      <f:errors path="*">
         <div class="alert alert-warning">Ops, alguma coisa está errada. Corrija os erros
            encontrados e tente novamente.</div>
      </f:errors>
      <div class="form-group">
         <label class="control-label col-lg-2" for="user">Nome</label>
         <div class="col-lg-10">
            <f:input path="name" cssClass="form-control" />
            <f:errors path="name" element="div" cssClass="text-danger" />
         </div>
      </div>
      <input type="submit" value="Salvar" class="btn btn-success" />
      <a href="../accounttypes" class="btn btn-default">Voltar</a>
   </f:form>
   <script type="text/javascript">
				$(document).ready(function() {
					$('.text-danger').parent().parent().addClass('has-error');
					$('.text-danger').parent().addClass('has-error');
				});
			</script>
</t:template>