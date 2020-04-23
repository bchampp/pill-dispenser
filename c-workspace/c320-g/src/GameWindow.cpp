#include <SDL.h>

#include <iostream>

#include "GameWindow.h"
#include "SpriteLoader.h"
#include "Graphics.h"

using namespace std;

const int FPS = 50;
const int MAX_FRAME_TIME = 1000 / FPS;

int main(int argv, char** args) {
	GameWindow game;
	return 0;
}

GameWindow::GameWindow() {
	SDL_Init(SDL_INIT_EVERYTHING);
	gameLoop();
}

void GameWindow::gameLoop() {
	Graphics graphics;
	SDL_Event event;
	SpriteLoader spriteLoader(graphics);

	background= graphics.loadImage("background");


	int LAST_UPDATE_TIME = SDL_GetTicks();

	while(true) {

		if (SDL_PollEvent(&event)) {
					if (event.type == SDL_KEYDOWN) {
						if (event.key.repeat == 0) {
						}
					}
					else if (event.type == SDL_KEYUP) {
					}
					else if (event.type == SDL_QUIT) {
						return;
					}
				}

		const int CURRENT_TIME_MS = SDL_GetTicks();
		int ELAPSED_TIME_MS = CURRENT_TIME_MS - LAST_UPDATE_TIME;

		this->graphics = graphics;
		this->gameUpdate(min(ELAPSED_TIME_MS, MAX_FRAME_TIME));
		LAST_UPDATE_TIME = CURRENT_TIME_MS;

		this->gameDraw(graphics);
	}
}

void GameWindow::gameUpdate(const float& elapsedTime) const {

}

void GameWindow::gameDraw(Graphics& graphics) {
	//graphics.clear();

	graphics.drawSurface(graphics.surfaceToTexture(background), NULL, NULL);

	graphics.flip();
}




