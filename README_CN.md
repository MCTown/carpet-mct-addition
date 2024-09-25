# Carpet-MCT-Addition

[English](README.md) | **中文**

这是一个Carpet的附属模组，正在为[MCTown服务器](http://www.mctown.tech/)提供支持。

默认的设定不会更改任何游戏特性。

## 索引

<!-- TOC -->

- [Carpet-MCT-Addition](#carpet-mct-addition)
    - [索引](#%E7%B4%A2%E5%BC%95)
    - [规则](#%E8%A7%84%E5%88%99)
        - [右键告示牌运行指令 runCommandOnSign](#%E5%8F%B3%E9%94%AE%E5%91%8A%E7%A4%BA%E7%89%8C%E8%BF%90%E8%A1%8C%E6%8C%87%E4%BB%A4-runcommandonsign)
        - [右键告示牌编辑文字 editableSign](#%E5%8F%B3%E9%94%AE%E5%91%8A%E7%A4%BA%E7%89%8C%E7%BC%96%E8%BE%91%E6%96%87%E5%AD%97-editablesign)
        - [打火石激活观察者 flintAndSteelActivatesObserver](#%E6%89%93%E7%81%AB%E7%9F%B3%E6%BF%80%E6%B4%BB%E8%A7%82%E5%AF%9F%E8%80%85-flintandsteelactivatesobserver)

<!-- /TOC -->

## 规则

### 右键告示牌运行指令 (runCommandOnSign)

当玩家不潜行并在主手没有物品的情况下右键点击告示牌且告示牌的文字内容以'/'开头时，将会执行告示牌上的指令。

- 类型: `boolean`
- 默认值: `false`
- 参考选项: `false`, `true`
- 分类: `CMA`, `SURVIVAL`

### 右键告示牌编辑文字 (editableSign)

玩家可以在主手没有物品的情况下通过潜行并右键告示牌来编辑其中的内容。

- 类型: `boolean`
- 默认值: `false`
- 参考选项: `false`, `true`
- 分类: `CMA`, `SURVIVAL`

### 打火石激活观察者 (flintAndSteelActivatesObserver)

打火石右键观察者的任意方向可以激活该观察者，潜行以在观察者上点火。

- 类型: `boolean`
- 默认值: `false`
- 参考选项: `false`, `true`
- 分类: `CMA`, `CREATIVE`
