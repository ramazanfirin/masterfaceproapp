(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('StaffTypeController', StaffTypeController);

    StaffTypeController.$inject = ['StaffType'];

    function StaffTypeController(StaffType) {

        var vm = this;

        vm.staffTypes = [];

        loadAll();

        function loadAll() {
            StaffType.query(function(result) {
                vm.staffTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
