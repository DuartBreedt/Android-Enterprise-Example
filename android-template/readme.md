# Android Template

## To Learn
- Custom typography for the material theme (e.g. define Style.smallTitle)
- Creating robust composable components (E.g. Button with text component in it, https://youtu.be/_qls2CEAbxI?si=rOLSK92_AbrVz5Tt&t=134)
  - LocalTextStyle
  - ProvideTextStyle
  - LocalContentColor (Automatically selects appropriate color for text if the button is dark or light)

## TODO
- Create plugin to extract common app/library set up
- Use clean architecture!
- Variants + env API domains + multiple BFFs
- RoomDB?
- Article on my set up.
- Article on why using plugins for dependencies in an enterprise environment discourages innovation and flexibility. 

# Concepts used
- Repository per module to manage various teams' access
- Fragment per screen, compose for UI
- Navigation strategy
- Gradle catalog for dependency management
- Plugin for library and application set up (TODO)
- Composite builds for local development (in lieu of mavenLocal repository)
- Sharing data models (data modules)
- DI with Hilt
- MVVM
  - LiveData, SharedFlow