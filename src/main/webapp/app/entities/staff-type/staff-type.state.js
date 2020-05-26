(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('staff-type', {
            parent: 'entity',
            url: '/staff-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'masterfaceproApp.staffType.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/staff-type/staff-types.html',
                    controller: 'StaffTypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('staffType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('staff-type-detail', {
            parent: 'staff-type',
            url: '/staff-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'masterfaceproApp.staffType.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/staff-type/staff-type-detail.html',
                    controller: 'StaffTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('staffType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'StaffType', function($stateParams, StaffType) {
                    return StaffType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'staff-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('staff-type-detail.edit', {
            parent: 'staff-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/staff-type/staff-type-dialog.html',
                    controller: 'StaffTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StaffType', function(StaffType) {
                            return StaffType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('staff-type.new', {
            parent: 'staff-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/staff-type/staff-type-dialog.html',
                    controller: 'StaffTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('staff-type', null, { reload: 'staff-type' });
                }, function() {
                    $state.go('staff-type');
                });
            }]
        })
        .state('staff-type.edit', {
            parent: 'staff-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/staff-type/staff-type-dialog.html',
                    controller: 'StaffTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StaffType', function(StaffType) {
                            return StaffType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('staff-type', null, { reload: 'staff-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('staff-type.delete', {
            parent: 'staff-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/staff-type/staff-type-delete-dialog.html',
                    controller: 'StaffTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StaffType', function(StaffType) {
                            return StaffType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('staff-type', null, { reload: 'staff-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
