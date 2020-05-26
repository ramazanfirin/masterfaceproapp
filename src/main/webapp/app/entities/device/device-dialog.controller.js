(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('DeviceDialogController', DeviceDialogController);

    DeviceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Device', 'Location'];

    function DeviceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Device, Location) {
        var vm = this;

        vm.device = entity;
        vm.clear = clear;
        vm.save = save;
        vm.locations = Location.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.device.id !== null) {
                Device.update(vm.device, onSaveSuccess, onSaveError);
            } else {
                Device.save(vm.device, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('masterfaceproApp:deviceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
