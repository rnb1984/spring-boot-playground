Home Service

This is a sub-repo to run the Home service. 

To run NEO4J db:
docker run -e NEO4J_dbms_security_auth__enabled=false -p 7474:7474 -p 7687:7687 -v ~/user_session_database_copy:/var/lib/neo4j/data neo4j:3.5.2 
