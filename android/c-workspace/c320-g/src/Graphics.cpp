#include <SDL.h>
#include <SDL_Image.h>
#include <iostream>

#include "Graphics.h"
#include "GameWindow.h"

using namespace std;

Graphics::Graphics() {
	SDL_CreateWindowAndRenderer(GameWindow::GAME_WIDTH, GameWindow::GAME_HEIGHT, 0, &this->window, &this->renderer);
	SDL_SetWindowTitle(this->window, "Swing man");
}

void Graphics::putImage(const string &name, const string &path) {
	if (sprites.count(path) == 0) {
		sprites[name] = IMG_Load(path.c_str());
	}
}

SDL_Surface* Graphics::loadImage(const string &name) {
	return sprites[name];
}

SDL_Texture* Graphics::surfaceToTexture(SDL_Surface* &surface) {
	return SDL_CreateTextureFromSurface(renderer, surface);
}



void Graphics::drawSurface(SDL_Texture* source, SDL_Rect* sourceRectangle, SDL_Rect* destinationRectangle) {
	SDL_RenderCopy(this->renderer, source, sourceRectangle, destinationRectangle);
}

void Graphics::flip() {
	SDL_RenderPresent(this->renderer);
}

void Graphics::clear() {
	SDL_RenderClear(this->renderer);
}

SDL_Renderer* Graphics::getRenderer() const {
	return renderer;
}




