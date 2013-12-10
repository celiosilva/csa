<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <t:language />
   <h2>
      Tpos de Contas Bancárias <a href="accounttypes/" class="btn btn-success">Criar Tipo de
         Conta</a>
   </h2>
   <table class="table table-striped table-hover">
      <tr>
         <th>Id</th>
         <th>Nome</th>
         <th>Actions</th>
      </tr>
      <c:forEach var="type" items="${accountTypes}">
         <tr>
            <td>${type.id}</td>
            <td>${type.name}</td>
            <td><f:form method="DELETE" action="accounttypes/${type.id}">
                  <div class="btn-group">
                     <a href="accounttypes/${type.id}" class="btn btn-info pull-left">Detalhar/Alterar</a>
                     <input type="submit" class="btn btn-warning" value="Remover"
                        onclick="return confirm('Deseja realmente remover esse tipo de conta?');" />
                  </div>
               </f:form></td>
         </tr>
      </c:forEach>
   </table>
</t:template>