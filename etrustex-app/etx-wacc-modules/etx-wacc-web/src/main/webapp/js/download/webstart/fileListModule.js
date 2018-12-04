'use strict';

FileListController.$inject = ['$scope', 'DataProvider', '$translate'];

function FileListController($scope, DataProvider, $translate) {
  $scope.isAllSelected = true;

  $scope.selectAll = function () {
    angular.forEach($scope.files, function (file) {
      file.isSelected = $scope.isAllSelected;
    });
  };

  $scope.fileSelected = function () {
    $scope.isAllSelected = $scope.files.every(function (file) {
      return file.isSelected;
    });
  };


  var init = function () {
    $scope.files = DataProvider.extractData(DataProvider.data.files);

    DataProvider.data.files = null;

    if ($scope.files != null) {
      angular.forEach($scope.files, function (file) {
        file.isSelected = true;
        file.isPayload = DataProvider.hasData(file.isPayload) && file.isPayload == 'true';
        file.comment = DataProvider.hasData(file.comment) ? file.comment : null;
        file.getAnchorId = function () {
          return 'F-' + file.id;
        };
      })
    }
  }

  $scope.showComment = function (comment) {
    var dialog = angular.element("<textarea class='comment-box' readonly rows='4' cols='50'>" + comment + "</textarea>");

    $(dialog)
      .dialog({
        resizable: false,
        autoOpen: false,
        modal: true,
        position: {my: 'center', at: 'center', of: window},
        title: $translate.instant('downloadView.messageDlg.title'),
        height: 'auto',
        width: 'auto',
        buttons: [
          {
            text: $translate.instant('commonView.optionPane.option.ok'),
            click: function () {
              $(this).dialog('close');
              $(this).remove();
            }
          }
        ]
      })
      .dialog('open');
  }


  init();
}

angular.module('etrustex.message.file-list', [])
  .controller('FileListController', FileListController);
