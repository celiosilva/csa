<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <t:language />
   <h2>
      Contas Bancárias <a href="accounts/" class="btn btn-success">Criar Conta</a>
   </h2>
   <table class="table table-striped table-hover">
      <tr>
         <th>Cliente</th>
         <th>Nr Conta</th>
         <th>Tipo Conta</th>
         <th>Saldo</th>
         <th>Actions</th>
      </tr>
      <c:forEach var="account" items="${accounts}">
         <tr>
            <td>${account.user.nome}</td>
            <td>${account.bankAccount}</td>
            <td>${account.accountType.name}</td>
            <td>${account.balance}</td>
            <td><f:form method="DELETE" action="accounts/${account.id}">
                  <div class="btn-group">
                     <a href="accounts/${account.id}" class="btn btn-info pull-left">Detalhar/Alterar</a>
                     <input type="submit" class="btn btn-warning" value="Remover"
                        onclick="return confirm('Deseja realmente remover esse usuário?');" />
                  </div>
               </f:form></td>
         </tr>
      </c:forEach>
   </table>
</t:template>