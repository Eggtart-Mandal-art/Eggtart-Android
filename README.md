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
    datastore
    network
    room
  end
  subgraph domain
    kakao
    mandalart
    user
  end
  subgraph features
    home
    login
    write-goal
  end
  datastore --> user
  login --> feature
  login --> util
  login --> user
  network --> kakao
  network --> user
  network --> mandalart
  network --> util
  user --> util
  feature --> util
  mandalart --> util
  features --> feature
  features --> util
  features --> home
  features --> login
  features --> write-goal
  features --> kakao
  features --> user
  features --> mandalart
  home --> feature
  home --> util
  home --> user
  home --> mandalart
  app --> features
  app --> di
  di --> util
  di --> room
  di --> network
  di --> datastore
  di --> mandalart
  di --> kakao
  di --> user
  room --> mandalart
  write-goal --> feature
  write-goal --> util
  write-goal --> mandalart
  write-goal --> user
```