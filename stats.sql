delimiter $$

CREATE DATABASE `stats` /*!40100 DEFAULT CHARACTER SET utf8 */$$

CREATE TABLE `config` (
  `force` tinyint(4) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

INSERT INTO `stats`.`config`
(`force`,
`id`)
VALUES
(
<{force: 0}>,
<{id: }>
);


CREATE TABLE `latest_stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stats_id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `value` varchar(500) DEFAULT NULL,
  `normal` tinyint(4) NOT NULL DEFAULT '1',
  `stateDescription` varchar(1000) NOT NULL,
  `when` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valueChanged` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE
DEFINER=`root`@`localhost`
TRIGGER `stats`.`update`
AFTER INSERT ON `stats`.`stats`
FOR EACH ROW
begin
	delete from latest_stats where name = new.name;
	insert into latest_stats(`name`, `stats_id`) values (new.name, new.id);
end
$$

