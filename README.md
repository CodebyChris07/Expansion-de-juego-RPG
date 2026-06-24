# Implementación de Inventario

## Objetivo
Aplicar los principios **SOLID** para construir un sistema de inventario escalable, mantenible y extensible sobre el código base existente del juego RPG.

## Descripción

El sistema de inventario permitirá a cada personaje del juego **almacenar, equipar y gestionar ítems** durante las partidas. Cada personaje contará con un inventario propio donde podrá guardar armas y armaduras obtenidas a lo largo del juego.

Los ítems tendrán atributos como **durabilidad, material y encantamientos**, lo que añade profundidad estratégica al combate. Un arma desgastada rendirá menos en batalla, mientras que un encantamiento puede marcar la diferencia entre ganar o perder.

El inventario estará diseñado para **escalar fácilmente**, permitiendo agregar nuevos tipos de ítems en el futuro (pociones, accesorios, materiales) sin necesidad de modificar la estructura existente.

## Estructura base

```
ItemBase (abstract)
├── Arma
│   ├── Espada
│   ├── Arco
│   └── Baston
└── Armadura
    ├── Pechera
    └── Pantalones

Inventario
└── gestiona List<ItemBase>
```
