package ru.vsu.csf.enlightened.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.enlightened.LightUp;
import ru.vsu.csf.enlightened.model.Field;
import ru.vsu.csf.enlightened.model.FieldCell;
import ru.vsu.csf.enlightened.model.FieldCellType;

import java.util.HashMap;

public class GameScreen extends AbsGameScreen {

    static final float MARGIN_LEFT = 70;
    static final float MARGIN_BOTTOM = 50;
    static final float CELL_SIZE = 50;


    Field field;
    HashMap<FieldCellType, TextureRegion> textures;
    //TextureRegion light;

    @Override
    public void show() {
        super.show();

        field = new Field() {{ init("levels/1.lul"); }};

        textures = new HashMap<FieldCellType, TextureRegion>() {{
            put(FieldCellType.REGULAR, new TextureRegion(new Texture(Gdx.files.internal("assets/cell.png"))));
            put(FieldCellType.BULB, new TextureRegion(new Texture(Gdx.files.internal("assets/light.png"))));
            put(FieldCellType.LIT, new TextureRegion(new Texture(Gdx.files.internal("assets/lit.png"))));
            put(FieldCellType.WALL, new TextureRegion(new Texture(Gdx.files.internal("assets/wall.png"))));
        }
        };

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                screenY = LightUp.HEIGHT - screenY;
                screenX -= MARGIN_LEFT;
                screenY -= MARGIN_BOTTOM;

                int i = (int) (screenX / CELL_SIZE);
                int j = (int) (screenY / CELL_SIZE);

                field.enlighten(i, j);

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        FieldCell[][] data = field.getData();

        batch.begin();
        for (int j = 0; j < data[0].length; j++) {
            for (int i = 0; i < data.length; i++) {
                batch.draw(textures.get(data[i][j].getType()), i*CELL_SIZE + MARGIN_LEFT, j*CELL_SIZE + MARGIN_BOTTOM, CELL_SIZE, CELL_SIZE);
            }
        }
        batch.end();
    }

}
