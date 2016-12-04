set GroupID=simple.app.cmmdc
set ArtifactID=cmmdc
set Version=1.0
mvn archetype:generate -DgroupId=%GroupID% -DartifactId=%ArtifactID% -Dversion=%Version% -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
