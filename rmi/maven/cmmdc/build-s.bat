set GroupID=cmmdc
set ArtifactID=server
set Version=1.0
mvn archetype:create -DgroupId=%GroupID% -DartifactId=%ArtifactID% -Dversion=%Version% -DarchetypeArtifactId=maven-archetype-quickstart
