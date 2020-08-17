use negocio;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET GLOBAL time_zone = '+3:00';


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `negocio`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `cpf` varchar(200) DEFAULT NULL,
  `cep` varchar(200) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `bairro` varchar(200) DEFAULT NULL,
  `cidade` varchar(200) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cpf`, `cep`, `endereco`, `bairro`, `cidade`, `estado`, `telefone`) VALUES
(11, 'DANIELA MARTINS   ', '62905172010', '65052080', 'Rua Dois, 176', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1113'),
(12, 'FERNANDO CAVALCANTE', '36120623876', '65047670', 'Vila Radional, 15', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1105'),
(13, 'ISABELA BENEDETTI', '34696243052', '65058014', 'Rua 21, 1', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1112'),
(14, 'JUNIOR DE ALMEIDA', '29892402847', '65051400', 'Rua Leitão Cunha, 22', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1106'),
(15, 'BERNARDINO FREDERICO', '25726087070', '65036660', 'Rua Souza Andrade, 99', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1099'),
(16, 'BOWMAN  SANTOS ', '23886581012', '65092035', 'Rua da Piçarra, 10', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1100'),
(17, 'CHRISTIANO RUBEM ', '14161745087', '65081367', 'Rua Vinte e Dois, 98', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1102'),
(18, 'GABRIEL ARAUJO ', '11067252045', '65092206', 'Rua Santa Luzia, 90', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1104'),
(19, 'DAIANA SANTOS DE FATIMA', '70772672024', '65051400', 'Rua Leitão Cunha, 2200', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1111'),
(20, 'ELAINE CRISTINA', '63619749019', '65055634', 'Travessa do Engenho,10', 'Vila Mariana', 'São Paulo', 'SP', '(11)99123-1115');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE IF NOT EXISTS `estoque` (
  `id` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `cod_filial` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`id`, `quantidade`, `cod_filial`, `cod_produto`) VALUES
(44, 50, 4, 25),
(45, 50, 4, 6),
(46, 50, 4, 8),
(47, 50, 4, 26),
(48, 50, 4, 29),
(49, 50, 5, 30),
(50, 50, 5, 4),
(51, 50, 5, 10),
(52, 50, 5, 5),
(53, 50, 5, 7),
(54, 50, 5, 28),
(55, 50, 5, 27),
(56, 50, 5, 25),
(57, 50, 5, 6),
(58, 50, 5, 8),
(59, 50, 5, 26),
(60, 50, 5, 29),
(61, 50, 6, 30),
(62, 50, 6, 4),
(63, 50, 6, 10),
(64, 50, 6, 5),
(65, 50, 6, 7),
(66, 50, 6, 28),
(67, 50, 6, 27),
(68, 50, 6, 25),
(69, 50, 6, 6),
(70, 50, 6, 8),
(71, 50, 6, 26),
(72, 50, 6, 29),
(73, 50, 7, 30),
(74, 50, 7, 4),
(75, 50, 7, 10),
(76, 50, 7, 5),
(77, 50, 7, 7),
(78, 50, 7, 28),
(79, 50, 7, 27),
(80, 50, 7, 25),
(81, 50, 7, 6),
(82, 50, 7, 8),
(83, 50, 7, 26),
(84, 50, 7, 29),
(85, 50, 8, 30),
(86, 50, 8, 4),
(87, 50, 8, 10),
(88, 50, 8, 5),
(89, 50, 8, 7),
(90, 50, 8, 28),
(91, 50, 8, 27),
(92, 50, 8, 25),
(93, 50, 8, 6),
(94, 50, 8, 8),
(95, 50, 8, 26),
(96, 50, 8, 29),
(97, 50, 9, 30),
(98, 50, 9, 4),
(99, 50, 9, 10),
(100, 50, 9, 5),
(101, 50, 9, 7),
(102, 50, 9, 28),
(103, 50, 9, 27),
(104, 50, 9, 25),
(105, 50, 9, 6),
(106, 50, 9, 8),
(107, 50, 9, 26),
(108, 50, 9, 29),
(109, 50, 10, 30),
(110, 50, 10, 4),
(111, 50, 10, 10),
(112, 50, 10, 5),
(113, 50, 10, 7),
(114, 50, 10, 28),
(115, 50, 10, 27),
(116, 50, 10, 25),
(117, 50, 10, 6),
(118, 50, 10, 8),
(119, 50, 10, 26),
(120, 50, 10, 29),
(121, 50, 11, 30),
(122, 50, 11, 4),
(123, 50, 11, 10),
(124, 50, 11, 5),
(125, 50, 11, 7),
(126, 50, 11, 28),
(127, 50, 11, 27),
(128, 50, 11, 25),
(129, 50, 11, 6),
(130, 50, 11, 8),
(131, 50, 11, 26),
(132, 50, 11, 29),
(133, 100, 12, 2),
(134, 100, 12, 13),
(135, 100, 12, 3),
(136, 100, 12, 20),
(137, 100, 12, 9),
(138, 100, 12, 15),
(139, 100, 12, 21),
(140, 100, 12, 1),
(141, 100, 12, 16),
(142, 100, 12, 17),
(143, 100, 12, 22),
(144, 100, 12, 23),
(145, 100, 12, 19),
(146, 100, 12, 18),
(147, 100, 12, 12),
(148, 100, 12, 11),
(149, 100, 12, 14),
(150, 100, 12, 24),
(151, 100, 13, 2),
(152, 100, 13, 13),
(153, 100, 13, 3),
(154, 100, 13, 20),
(155, 100, 13, 9),
(156, 100, 13, 15),
(157, 100, 13, 21),
(158, 100, 13, 1),
(159, 100, 13, 16),
(160, 100, 13, 17),
(161, 100, 13, 22),
(162, 100, 13, 23),
(163, 100, 13, 19),
(164, 100, 13, 18),
(165, 100, 13, 12),
(166, 100, 13, 11),
(167, 100, 13, 14),
(168, 100, 13, 24),
(169, 100, 14, 2),
(170, 100, 14, 13),
(171, 100, 14, 3),
(172, 100, 14, 20),
(173, 100, 14, 9),
(174, 100, 14, 15),
(175, 100, 14, 21),
(176, 100, 14, 1),
(177, 100, 14, 16),
(178, 100, 14, 17),
(179, 100, 14, 22),
(180, 100, 14, 23),
(181, 100, 14, 19),
(182, 100, 14, 18),
(183, 100, 14, 12),
(184, 100, 14, 11),
(185, 100, 14, 14),
(186, 100, 14, 24),
(187, 100, 15, 2),
(188, 100, 15, 13),
(189, 100, 15, 3),
(190, 100, 15, 20),
(191, 100, 15, 9),
(192, 100, 15, 15),
(193, 100, 15, 21),
(194, 100, 15, 1),
(195, 100, 15, 16),
(196, 100, 15, 17),
(197, 100, 15, 22),
(198, 100, 15, 23),
(199, 100, 15, 19),
(200, 100, 15, 18),
(201, 100, 15, 12),
(202, 100, 15, 11),
(203, 100, 15, 14),
(204, 100, 15, 24),
(205, 100, 16, 2),
(206, 100, 16, 13),
(207, 100, 16, 3),
(208, 100, 16, 20),
(209, 100, 16, 9),
(210, 100, 16, 15),
(211, 100, 16, 21),
(212, 100, 16, 1),
(213, 100, 16, 16),
(214, 100, 16, 17),
(215, 100, 16, 22),
(216, 100, 16, 23),
(217, 100, 16, 19),
(218, 100, 16, 18),
(219, 100, 16, 12),
(220, 100, 16, 11),
(221, 100, 16, 14),
(222, 100, 16, 24),
(223, 100, 17, 2),
(224, 100, 17, 13),
(225, 100, 17, 3),
(226, 100, 17, 20),
(227, 100, 17, 9),
(228, 100, 17, 15),
(229, 100, 17, 21),
(230, 100, 17, 1),
(231, 100, 17, 16),
(232, 100, 17, 17),
(233, 100, 17, 22),
(234, 100, 17, 23),
(235, 100, 17, 19),
(236, 100, 17, 18),
(237, 100, 17, 12),
(238, 100, 17, 11),
(239, 100, 17, 14),
(240, 100, 17, 24),
(241, 100, 18, 2),
(242, 100, 18, 13),
(243, 100, 18, 3),
(244, 100, 18, 20),
(245, 100, 18, 9),
(246, 100, 18, 15),
(247, 100, 18, 21),
(248, 100, 18, 1),
(249, 100, 18, 16),
(250, 100, 18, 17),
(251, 100, 18, 22),
(252, 100, 18, 23),
(253, 100, 18, 19),
(254, 100, 18, 18),
(255, 100, 18, 12),
(256, 100, 18, 11),
(257, 100, 18, 14),
(258, 100, 18, 24),
(259, 100, 19, 2),
(260, 100, 19, 13),
(261, 100, 19, 3),
(262, 100, 19, 20),
(263, 100, 19, 9),
(264, 100, 19, 15),
(265, 100, 19, 21),
(266, 100, 19, 1),
(267, 100, 19, 16),
(268, 100, 19, 17),
(269, 100, 19, 22),
(270, 100, 19, 23),
(271, 100, 19, 19),
(272, 100, 19, 18),
(273, 100, 19, 12),
(274, 100, 19, 11),
(275, 100, 19, 14),
(276, 100, 19, 24),
(277, 100, 20, 2),
(278, 100, 20, 13),
(279, 100, 20, 3),
(280, 100, 20, 20),
(281, 100, 20, 9),
(282, 100, 20, 15),
(283, 100, 20, 21),
(284, 100, 20, 1),
(285, 100, 20, 16),
(286, 100, 20, 17),
(287, 100, 20, 22),
(288, 100, 20, 23),
(289, 100, 20, 19),
(290, 100, 20, 18),
(291, 100, 20, 12),
(292, 100, 20, 11),
(293, 100, 20, 14),
(294, 100, 20, 24),
(300, 10, 21, 2),
(311, 2, 23, 33),
(312, 1, 23, 37),
(313, 6, 23, 32);

-- --------------------------------------------------------

--
-- Estrutura da tabela `filial`
--

DROP TABLE IF EXISTS `filial`;
CREATE TABLE IF NOT EXISTS `filial` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `filial`
--

INSERT INTO `filial` (`id`, `nome`) VALUES
(21, 'Jardim Tropical'),
(22, 'Araçagy'),
(23, 'Maiobão'),
(24, 'João Paulo'),
(25, 'Tirirical'),
(26, 'Shopping da Ilha'),
(27, 'Coahatrac IV'),
(28, 'Pátio Norte'),
(29, 'Hiper Renascença'),
(30, 'Cajazeiras'),
(31, 'Cohab'),
(32, 'Calhau'),
(33, 'Cidade Operária'),
(34, 'Cohama'),
(35, 'Rio Anil'),
(36, 'Super Turu'),
(37, 'Turu'),
(38, 'Bacanga'),
(39, 'Cohatrac'),
(40, 'Carone Anjo da Guarda');

-- --------------------------------------------------------

--
-- Estrutura da tabela `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
CREATE TABLE IF NOT EXISTS `forma_pagamento` (
  `id` int(11) NOT NULL,
  `tipo` varchar(200) DEFAULT NULL,
  `parcela` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `forma_pagamento`
--

INSERT INTO `forma_pagamento` (`id`, `tipo`, `parcela`) VALUES
(41, 'A vista', 1),
(42, 'Boleto', 1),
(43, 'Cartão de Crédito', 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(314),
(314),
(314),
(314),
(314),
(314),
(314),
(314);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens_pedido`
--

DROP TABLE IF EXISTS `itens_pedido`;
CREATE TABLE IF NOT EXISTS `itens_pedido` (
  `id` int(11) NOT NULL,
  `codPedido` int(11) DEFAULT NULL,
  `codProduto` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `cod_pedido` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itens_pedido`
--

INSERT INTO `itens_pedido` (`id`, `codPedido`, `codProduto`, `status`, `quantidade`, `valor`, `total`, `cod_pedido`, `cod_produto`) VALUES
(296, NULL, NULL, 'Processado', 20, 22, 440, 295, 1),
(298, NULL, NULL, 'cancelado', 20, 22, 440, 297, 1),
(299, NULL, NULL, 'Processado', 20, 33, 660, 297, 2),
(305, NULL, NULL, 'Processado', 10, 30, 300, 304, 2),
(307, NULL, NULL, 'cancelado', 5, 2349, 11745, 306, 32),
(308, NULL, NULL, 'Processado', 2, 1899, 3798, 306, 33),
(309, NULL, NULL, 'Processado', 1, 799, 799, 306, 37),
(310, NULL, NULL, 'Processado', 6, 2349, 14094, 306, 32);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_estoque`
--

DROP TABLE IF EXISTS `pedido_estoque`;
CREATE TABLE IF NOT EXISTS `pedido_estoque` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `codCliente` int(11) DEFAULT NULL,
  `codUsuario` int(11) DEFAULT NULL,
  `codFilial` int(11) DEFAULT NULL,
  `codPagamento` int(11) DEFAULT NULL,
  `Observacao` varchar(300) DEFAULT NULL,
  `itens` int(11) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_filial` int(11) NOT NULL,
  `cod_pagamento` int(11) NOT NULL,
  `cod_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido_estoque`
--

INSERT INTO `pedido_estoque` (`id`, `tipo`, `codCliente`, `codUsuario`, `codFilial`, `codPagamento`, `Observacao`, `itens`, `valor`, `cod_cliente`, `cod_filial`, `cod_pagamento`, `cod_usuario`) VALUES
(295, 'saida', NULL, NULL, NULL, NULL, 'nois bixo', 30, 660, 11, 21, 1, 1),
(297, 'entrada', NULL, NULL, NULL, NULL, 'nois bixo 2', 0, 220, 11, 21, 1, 1),
(304, 'saida', NULL, NULL, NULL, NULL, 'descricao', 10, 300, 11, 21, 1, 1),
(306, 'entrada', NULL, NULL, NULL, NULL, 'nois bixo', 9, 18691, 11, 23, 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `codbarra` int(20) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `descricao`, `codbarra`, `valor`) VALUES
(31, 'Notebook Gamer Acer 9ºGer.Intel Core i5-9300H', 6102905, 4699),
(32, 'Iphone 7 128GB Preto Matte 4G', 5175135, 2349),
(33, 'Smart TV LED 43\" LG 43LM6300PSB Full HD', 5762626, 1899),
(34, 'Máquina de Lavar Electrolux 13Kg Branca LAC13', 5112648, 1459),
(35, 'Notebook Intel K131 Apollo Lake', 14324149, 1270),
(36, 'Geladeira Electrolux RE31 240 Litros Branco 1 Porta 127V', 13543481, 1229),
(37, 'Fogão de Piso 4 Bocas Branco Consul CFO4NAB - Bivolt', 9775617, 799),
(38, 'Micro-ondas Electrolux MTD30 20 Litros Branco 110V', 15244331, 449),
(39, 'Fritadeira Elétrica sem Óleo 3,2 Litros Philco Air Fry', 8647143, 389),
(40, 'Mouse com Fio Laser Logitech USB', 5933552, 349),
(41, 'Depurador de Parede de Aço Preto Suggar DI61PT 60cm 110V', 14140572, 339.9),
(42, 'Pneu Aro 14 185/65R14 Goodyear Direction Sport', 9583947, 269.9),
(43, 'Vinho Tinto Yllum Malbec 750ml', 5238129, 49),
(44, 'Kit Shampoo e Condicionador Aussie Smooth Anti Frizz', 5529158, 36.38),
(45, 'Fralda Huggies G', 4887697, 29.9),
(46, 'Papel Higiênico Folha Dupla 30 Metros', 8900329, 24),
(47, 'Fraldas Pampers Recém-Nascido', 5022274, 20.9),
(48, 'Espuma de Barbear Gillette Foam Menthol 175g', 7460333, 20.5),
(49, 'Azeite Português Extra Virgem Andorinha Dorinha 500ml', 9731075, 20),
(50, 'Vinho Tinto Saint Lambert Selecion 700ml', 809438, 19.99),
(51, 'Arroz Branco Longo-fino Tipo 1 Camil Todo Dia 5Kg', 115789, 17.3),
(52, 'Feijão Carioca Tipo 1 Broto Legal 1 Kg', 4883977, 9.49),
(53, 'Queijo Parmesão Ralado Vigor 100g', 148318, 8.9),
(54, 'Queijo Mussarela Fatiado Sadia 200g', 9685880, 8.4),
(55, 'Suco de Laranja Integral Refrigerado Natural One 100% Suco 900ml', 9427058, 7.9),
(56, 'Ovos Vermelhos Grande com 12 Unidades', 8453640, 7.79),
(57, 'Batata Frita Lays Chips Original Lays 96g', 9944303, 6.49),
(58, 'Coca-Cola sem Açúcar 1,5 Litros', 7841620, 5.49),
(59, 'Óleo de Soja Liza 900ml', 406830, 4.3),
(60, 'Mortadela Defumada Fatiado Sadia', 9738606, 3.45),
(303, 'Novo produto', 24334535, 3333.22);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `cpf` varchar(200) DEFAULT NULL,
  `senha` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `cpf`, `senha`) VALUES
(1, 'GIOVANNA CONVERSANO   ', '42782712047', '1234'),
(2, 'VANESSA ROSA   ', '18561523085', '1234'),
(3, 'VIVIANE LOPES', '57123919025', '1234'),
(4, 'ANDREA CRISTINA', '25486291043', '1234'),
(5, 'MARCIEL JUNIOR ', '41276678002', '1234'),
(6, 'PEDRO MEDEIROS', '26186024040', '1234'),
(7, 'RAUL OTERO ', '53367332020', '1234'),
(8, 'THIAGO DA SILVA', '97832759097', '1234'),
(9, 'THIAGO DE CARVALHO', '93471243038', '1234'),
(10, 'NIKOLLE MAHY   ', '33204561054', '1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
