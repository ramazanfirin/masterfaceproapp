(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('StaffTypeDialogController', StaffTypeDialogController);

    StaffTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'StaffType'];

    function StaffTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, StaffType) {
        var vm = this;

        vm.staffType = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.staffType.id !== null) {
                StaffType.update(vm.staffType, onSaveSuccess, onSaveError);
            } else {
                StaffType.save(vm.staffType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('masterfaceproApp:staffTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
