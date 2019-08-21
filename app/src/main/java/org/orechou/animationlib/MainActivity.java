package org.orechou.animationlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import org.orechou.animationlib.elements.ArcRotateAlphaElement;
import org.orechou.animationlib.elements.ArcRotateElement;
import org.orechou.animationlib.elements.CircleArcRotateElement;
import org.orechou.animationlib.elements.CircleGridWaveElement;
import org.orechou.animationlib.elements.CircleWaveElement;
import org.orechou.animationlib.elements.PacmanElement;
import org.orechou.animationlib.elements.SquareRotateElement;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(new RecyclerView.Adapter<ElementHolder>() {

            @NonNull
            @Override
            public ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.item_element, parent, false);
                return new ElementHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ElementHolder holder, int position) {
                String elementName = ELEMENTS[position];
                Element element;
                switch (elementName) {
                    case ArcRotateElement.TAG:
                        element = new ArcRotateElement();
                        break;
                    case CircleArcRotateElement.TAG:
                        element = new CircleArcRotateElement();
                        break;
                    case CircleWaveElement.TAG:
                        element = new CircleWaveElement();
                        break;
                    case CircleGridWaveElement.TAG:
                        element = new CircleGridWaveElement();
                        break;
                    case PacmanElement.TAG:
                        element = new PacmanElement();
                        break;
                    case SquareRotateElement.TAG:
                        element = new SquareRotateElement();
                        break;
                    case ArcRotateAlphaElement.TAG:
                        element = new ArcRotateAlphaElement();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + elementName);
                }
                holder.elementView.setElement(element);
            }

            @Override
            public int getItemCount() {
                return ELEMENTS.length;
            }
        });

    }

    final static class ElementHolder extends RecyclerView.ViewHolder{

        public ElementView elementView;

        public ElementHolder(View itemView) {
            super(itemView);
            elementView = itemView.findViewById(R.id.element_view);
        }
    }

    private static final String[] ELEMENTS = new String[] {
            ArcRotateElement.TAG,
            CircleArcRotateElement.TAG,
            CircleWaveElement.TAG,
            CircleGridWaveElement.TAG,
            PacmanElement.TAG,
            SquareRotateElement.TAG,
            ArcRotateAlphaElement.TAG
    };
}
