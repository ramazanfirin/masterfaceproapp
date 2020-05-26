(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('RecordDetailController', RecordDetailController);

    RecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Record', 'Device', 'Image', 'Person'];

    function RecordDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Record, Device, Image, Person) {
        var vm = this;

        vm.record = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('masterfaceproApp:recordUpdate', function(event, result) {
            vm.record = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
