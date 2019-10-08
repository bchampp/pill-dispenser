
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdlib.h>

#include "SpriteLoader.h"

using namespace std;

SpriteLoader::SpriteLoader(Graphics& graphics) {
	initiliaze(graphics);
}

void SpriteLoader::initiliaze(Graphics& graphics) {
	string fileName("res/spriteLoader.txt");
	ifstream fileIn(fileName);
	string line;


	if (fileIn.fail()) {
		cerr << "Error" << endl;
	}

	string delimeter("\t");
	string name;
	string path;

	//This loop is to split insult combinations into
	//single strings by using the tab delimeter
	while (getline(fileIn, line)) {
		size_t start(0), end;

		int index(0);

		while ((end = line.find(delimeter, start)) != string::npos) {
			string contents = line.substr(start, end-start);
			start = end + delimeter.length();
			switch(index) {
			case 0:
				name = contents;
				break;
			}
			index++;
		}

		path = line.substr(start);
		graphics.putImage(name, path);
		cout << name << path << endl;
	}

	fileIn.close();
}



