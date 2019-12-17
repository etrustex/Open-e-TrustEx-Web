<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
  $(function() {
    $('#certificateInput').change(function () {
      clearSelectedIdentity();
      var file = this.files[0];
      if (file) {
        if (file.size > 100000) {
          GlobalAccess.common.displayError(GlobalAccess.i18.I18Message.MESSAGE_CERTIFICATE_FILE_MAX_SIZE);
          return;
        }
        $('#certificateInput-info').html(this.files[0].name);
      }
    })

    function clearSelectedIdentity () {
      GlobalAccess.crypto.setSelectedIdentity(false);
      $('#p12-identities').addClass('collapse');
      $('#certificatePassword').val('');
    }

    $('#loadIdentitiesBtn').click(function () {

      GlobalAccess.common.cleanErrorMessages();
      GlobalAccess.crypto.setSelectedIdentity(false);

      var password = $('#certificatePassword').val()
      if(password == '') {
        GlobalAccess.common.displayError(GlobalAccess.i18.I18Message.MESSAGE_CERTIFICATE_INSERT_PASSWORD);
        return;
      }
      GlobalAccess.crypto.PKCSReader({
        password: password,
        certificateFile: $('#certificateInput').prop('files')[0],
        checkValidityRange: false
      })
        .then(function (identities) {
          var oldBody = document.getElementById('identitiesTable').tBodies[0];
          var newBody = document.createElement('tbody');

          //p12Input_loadedIdentities = identities;
          var i = 0;
          identities.forEach(function (identity, localKey) {
            var row = newBody.insertRow(i++),
              cellAlias = row.insertCell(0),
              cellSN = row.insertCell(1),
              cellVF = row.insertCell(2),
              cellVU = row.insertCell(3);
            row.id = localKey;
            row.onclick = function () {

              GlobalAccess.crypto.setSelectedIdentity(identity);
              console.log('Identity ' + identity.friendlyName + ' selected!');
              $('.selected-identity').removeClass('selected-identity')
              row.classList.add('selected-identity')
            }
            row.classList.add('identity-row')

            cellAlias.innerHTML = identity.friendlyName;
            cellSN.innerHTML = identity.subject.join('; ');
            cellVF.innerHTML = identity.notBefore.toLocaleString();
            cellVU.innerHTML = identity.notAfter.toLocaleString();

            switch (GlobalAccess.crypto.checkValidityTimeRange(identity.cert)) {
              case GlobalAccess.crypto.CERTIFICATE_VALIDITY.EXPIRED:
                row.setAttribute('title', messages[GlobalAccess.i18.I18Tooltip.TOOLTIP_IDENTITY_EXPIRED]);
                row.classList.add('identity-invalid');
                break;
              case GlobalAccess.crypto.CERTIFICATE_VALIDITY.NOT_YET_VALID:
                row.setAttribute('title',  messages[GlobalAccess.i18.I18Tooltip.TOOLTIP_IDENTITY_NOT_YET_VALID]);
                row.classList.add('identity-invalid');
                break;
              default:
                break;
            }

            if(identities.size == 1) {
              //automatically selecting the only available identity!
              row.click();
            }
          })
          oldBody.parentNode.replaceChild(newBody, oldBody);
          $('#p12-identities').removeClass('collapse');
        })
        .catch(function (errorMessage) {
          GlobalAccess.common.displayError(GlobalAccess.i18.I18Message.MESSAGE_CERTIFICATE_FILE_NOT_VALID);
          clearSelectedIdentity();
        })
    })
  });
</script>
<div class="collapse" id="p12-input">
    <div>
        <div class="grid">
            <div class="grid-row">
                <span class="grid-cell header-label"><spring:message code="label.certificate"/>:</span>
                <span class="grid-cell mandatory-value-container">
                 <span class="btn btn-info btn-outline btn-sm fileinput-button">
                    <i class="glyphicon glyphicon-search"></i>
                    <span><spring:message code="label.select.certificate"/></span>
                    <input type="file" id="certificateInput" class="addFiles">
                </span>
                <span class='label label-info' id="certificateInput-info"></span>
            </span>
            </div>
            <div class="grid-row">
                <span class="grid-cell header-label"><spring:message code="label.password"/>:</span>
                <span class="grid-cell mandatory-value-container">
                <input type="password" id="certificatePassword" placeholder="Password">
                <button type="button" class="btn btn-primary btn-outline btn-sm" id="loadIdentitiesBtn" ng-disabled="false"><spring:message code="label.load.identities"/></button>
            </span>
            </div>
        </div>
    </div>
    <div id="p12-identities" class="collapse">
        <div class="grid">
            <div class="grid-row">
                <span class="grid-cell header-label"><spring:message code="label.certificate.identities"/>:</span>
            </div>
        </div>
        <div class="grid">
            <div class="grid-row">
                <table id="identitiesTable" class="row-selectable-table">
                    <thead>
                    <tr>
                        <th>Alias</th>
                        <th><spring:message code="label.subject.name"/></th>
                        <th><spring:message code="label.valid.from"/></th>
                        <th><spring:message code="label.valid.until"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>