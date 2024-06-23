package app.netlify.dev4rju9.newsapp.db

import androidx.room.TypeConverter
import app.netlify.dev4rju9.newsapp.models.Source

class Converters {

    @TypeConverter
    fun fromSource (source: Source) = source.name

    @TypeConverter
    fun toSource (name: String) = Source(name, name)

}