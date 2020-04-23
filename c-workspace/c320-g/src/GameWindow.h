#pragma once

using namespace std;

#include "Graphics.h"

class GameWindow {

public:

	static const int GAME_HEIGHT = 500;
	static const int GAME_WIDTH = 800;

	GameWindow();

	void gameLoop();

	void gameUpdate(const float& elapsedTime) const;

	void gameDraw(Graphics& graphics);

private:
	Graphics graphics;

	SDL_Surface* background;
};






