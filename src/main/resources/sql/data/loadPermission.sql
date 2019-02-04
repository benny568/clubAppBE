use odalybr_register;

INSERT INTO `permission` (`permission_id`, `userid`, `permission`) VALUES
(1, 3, 'user:view'),
(2, 4, 'user:create'),
(3, 4, 'user:read'),
(4, 4, 'user:update'),
(5, 4, 'user:delete'),
(6, 4, 'admin:read'),
(7, 4, 'team:read'),
(8, 4, 'team:update'),
(9, 4, 'team:delete'),
(10, 4, 'team:create');