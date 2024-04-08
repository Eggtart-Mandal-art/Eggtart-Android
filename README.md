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
  features --> feature
  features --> home
  features --> login
  features --> write-goal
  home --> feature
  home --> mandalart
  app --> features
  room --> mandalart
  write-goal --> feature
  write-goal --> util
```