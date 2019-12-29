# Subs App
Program for learning new English words from files with English subtitles. 

## Usage

start database:
cd /c/programs/h2/bin
java -jar h2-1.4.199.jar

run main method:
send path to dictionary as command line argument

## Commands
Register:
```bash
reg <username> <password>
```
Log in:
```bash
login <username> <password>
```
Logout:
```bash
logout
```
Exit program:
```bash
exit
```
Add file with subtitles:
```bash
add <filepath> <filename>
```
Show all words of user / Show words of user by file ID:
```bash
words
words <fileId> 
```
Show ID and name of user's files 
```bash
movies
```