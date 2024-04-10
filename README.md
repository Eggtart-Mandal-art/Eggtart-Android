# Eggtart-Android

EggTart Module Dependency Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph TB

  subgraph common
    feature
    util
  end
  subgraph core
    network
    room
  end
  subgraph domain
    mandalart
  end
  subgraph features
    home
    login
    write-goal
  end
  login --> feature
  home --> feature
  home --> mandalart
  app --> features
  app --> di
  di --> util
  di --> room
  di --> network
  di --> mandalart
  room --> mandalart
  write-goal --> feature
  write-goal --> util
```