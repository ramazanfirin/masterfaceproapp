(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('StaffTypeDeleteController',StaffTypeDeleteController);

    StaffTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'StaffType'];

    function StaffTypeDeleteController($uibModalInstance, entity, StaffType) {
        var vm = this;

        vm.staffType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            StaffType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
